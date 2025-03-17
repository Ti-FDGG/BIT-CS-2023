
//自定义的接收三个参数的泛型函数式接口
@FunctionalInterface
public interface TriFunction<T, U, V, R> {
    R apply(T t, U u, V v);
}

