#  -*- encoding: utf-8 -*-

"""This script attempts to illustrate how to use the gplk bindings to
solve some linear programming problems and to check if integer
programming problems can also be solved.

The test case is taken from Bazaraa, Example 3.6, pág 102 (111 pdf).

"""

import glpk


lp = glpk.LPX()
lp.name = "Problema Ejemplo 3.6"
lp.obj.maximize = False

# min -3 * x1 + x2
# s.t.  1 * x1 + 2 * x2 + x3  = 4
#      -1 * x1 +     x2      + x4 =1
#      x >= 0


# # Lo siguiente funciona correctamente
# lp.rows.add(2)
# lp.rows[0].bounds = 4.0, 4.0
# lp.rows[1].bounds = 1.0, 1.0

lp.rows.add(2)
lp.rows[0].bounds = 4.0
lp.rows[1].bounds = 1.0

lp.cols.add(4)
for c in lp.cols:
    c.name = "x{0}".format(c.index + 1)
    c.bounds = 0.0, None

lp.obj[:2] = [-3.0, 1.0]        # this should work


lp.matrix = [ (0,0, 1.0), (0,1, 2.0), (0,2, 1.0),
              (1,0, -1.0), (1,1, 1.0), (1,3, 1.0) ]

lp.simplex()

print "Z = {0}".format(lp.obj.value)
print "; ".join('{0} = {1}'.format(c.name, c.primal) for c in lp.cols)
