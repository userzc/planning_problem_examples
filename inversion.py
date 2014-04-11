#  -*- encoding: utf-8 -*-
import glpk

lp = glpk.LPX()
lp.name = "Problema de Inversión de presupuesto"
lp.obj.maximize = True

# Se tiene que formular como obtener la solución mediante programación
# lineal en caso de que sea posible. Para este problema si es posible.

lp.rows.add(5)
# límites
for r in lp.rows:
    r.bounds = 0.0
    r.name = "Year{0}".format(r.index)
lp.rows[0].bounds = 2200.0

lp.cols.add(10)
for c in lp.cols:
    c.name = "x{0}{1}".format(c.index / 2, (c.index) % 2 + 1)
    c.bounds = 0.0, None

lp.cols[-1].name = "x23"

# restricciones
lp.matrix = [
    # x01 + x02 = 2200
    (0, 0, 1.0), (0, 1, 1.0),
    # x11 + x12 = 1.08 x01
    (1, 2, 1.0), (1, 3, 1.0), (1, 0, -1.08),
    # x21 + x22 + x23 = 1.08 x11 + 1.17 x02
    (2, 4, 1.0), (2, 5, 1.0), (2, 9, 1.0), (2, 2, -1.08), (2, 1, -1.17),
    # x31 + x32 = 1.08 x21 + 1.17 x12
    (3, 6, 1.0), (3, 7, 1.0), (3, 4, -1.08), (3, 3, -1.17),
    # x41 = 1.08 x31 + 1.17 x22
    (4, 8, 1.0), (4, 6, -1.08), (4, 5, -1.17)
]

# función objetivo
# max (1.17 x32 + 1.08 x41 + 1.27 x23)
lp.obj[7, 8, 9] = [1.17, 1.08, 1.27]

lp.simplex()
# lp.interior()
# lp.intopt()

print "Z = {0}".format(lp.obj.value)
print "\n".join('{0}[{2}] = {1}'.format(c.name, c.primal, c.index) for
                c in lp.cols)
