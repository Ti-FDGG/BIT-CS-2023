import math
def round_by_tolerence(value, tolerance):
    return round(value, -int(math.log10(tolerance)))
