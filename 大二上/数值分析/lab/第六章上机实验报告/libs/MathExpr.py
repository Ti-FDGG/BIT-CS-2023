import math

class MathExpression:
    class Node:
        def __init__(self, node_type, value=None, children=None):
            self.node_type = node_type
            self.value = value
            self.children = children or []
    
    def __init__(self, expression=None):
        self.ast = None
        if expression:
            self.ast = self._parse(expression)
    
    def __call__(self, *args):
        """
        使 MathExpression 对象可以像函数一样被调用。支持多元变量。
        :param args: 自变量的值，按顺序传入
        :return: 计算表达式在给定的自变量处的值
        """
        return self.evaluate(dict(zip(self.get_variables(), args)))
    
    def to_string(self, node=None):
        """ 将 AST 节点转换为字符串 """
        if node is None:
            node = self.ast
        if node.node_type == "number":
            return str(node.value)
        elif node.node_type == "constant":
            return node.value
        elif node.node_type == "variable":
            return node.value
        elif node.node_type == "function":
            return f"{node.value}({', '.join([self.to_string(child) for child in node.children])})"
        elif node.node_type == "operator":
            left = self.to_string(node.children[0])
            right = self.to_string(node.children[1])
            return f"({left} {node.value} {right})"
        else:
            raise ValueError(f"Unknown node type: {node.node_type}")
    
    def get_variables(self):
        """ 获取表达式中所有的变量名 """
        variables = set()
        
        def _collect_vars(node):
            if node.node_type == "variable":
                variables.add(node.value)
            elif node.node_type == "function":
                for child in node.children:
                    _collect_vars(child)
            elif node.node_type == "operator":
                for child in node.children:
                    _collect_vars(child)
        
        _collect_vars(self.ast)
        return list(variables)
    
    def _parse(self, expression):
        tokens = self._tokenize(expression)
        return self._build_ast(tokens)
    
    def _tokenize(self, expression):
        import re
        pattern = r"[\d\.]+|[a-zA-Z]+|[\+\-\*/\^\(\),]"
        return re.findall(pattern, expression)
    
    def _build_ast(self, tokens):
        def parse_expression(index):
            node, index = parse_term(index)
            while index < len(tokens) and tokens[index] in "+-":
                op = tokens[index]
                index += 1
                right, index = parse_term(index)
                node = MathExpression.Node("operator", op, [node, right])
            return node, index

        def parse_term(index):
            node, index = parse_factor(index)
            while index < len(tokens) and tokens[index] in "*/":
                op = tokens[index]
                index += 1
                right, index = parse_factor(index)
                node = MathExpression.Node("operator", op, [node, right])
            return node, index

        def parse_factor(index):
            node, index = parse_base(index)
            while index < len(tokens) and tokens[index] == "^":
                index += 1
                exponent, index = parse_factor(index)
                node = MathExpression.Node("operator", "^", [node, exponent])
            return node, index

        def parse_base(index):
            token = tokens[index]
            if token.isdigit() or token.replace('.', '', 1).isdigit():
                return MathExpression.Node("number", float(token)), index + 1
            elif token.isalpha():
                if token in ("sin", "cos", "tan", "log"):
                    index += 1
                    child, index = parse_base(index)
                    return MathExpression.Node("function", token, [child]), index
                elif token in ("e", "pi"):
                    return MathExpression.Node("constant", token), index + 1
                else:
                    return MathExpression.Node("variable", token), index + 1
            elif token == "(":
                index += 1
                node, index = parse_expression(index)
                if tokens[index] == ")":
                    index += 1
                return node, index
            elif token == "-":
                child, index = parse_base(index + 1)
                return MathExpression.Node("operator", "-", [MathExpression.Node("number", 0), child]), index
            elif token == ",":
                # 支持多元函数参数分隔符
                return None, index + 1
            else:
                raise ValueError(f"Unexpected token: {token}")

        root, _ = parse_expression(0)
        return root

    def evaluate(self, variables=None):
        """ 计算表达式的值 """
        def _eval(node):
            if node.node_type == "number":
                return node.value
            elif node.node_type == "constant":
                return math.e if node.value == "e" else math.pi
            elif node.node_type == "variable":
                return variables.get(node.value, 0)
            elif node.node_type == "function":
                # 对于多元函数，递归处理所有子节点
                args = [ _eval(child) for child in node.children ]
                return getattr(math, node.value)(*args)
            elif node.node_type == "operator":
                left = _eval(node.children[0])
                right = _eval(node.children[1])
                if node.value == "+":
                    return left + right
                elif node.value == "-":
                    return left - right
                elif node.value == "*":
                    return left * right
                elif node.value == "/":
                    return left / right
                elif node.value == "^":
                    return left ** right
            else:
                raise ValueError(f"Unknown node type: {node.node_type}")
        
        if not self.ast:
            raise ValueError("Expression not parsed.")
        return _eval(self.ast)
    
    def derivative(self, var, order=1, h=1e-5):
        """ 使用数值微分的方式求导，var 为求导的变量，order 为求导的阶数 """
        def _numerical_derivative(x, current_order, h):
            if current_order == 1:
                result = (self.evaluate({var: x + h}) - self.evaluate({var: x - h})) / (2 * h)
            else:
                result = (_numerical_derivative(x + h, current_order - 1, h) - _numerical_derivative(x - h, current_order - 1, h)) / (2 * h)
            
            return result
        
        def derivative_function(x):
            return _numerical_derivative(x, order, h)
        
        return derivative_function


