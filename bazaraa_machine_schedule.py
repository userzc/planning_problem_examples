#  -*- encoding: utf-8 -*-
import glpk
from itertools import chain

lp = glpk.LPX()
lp.name = "Problema de Calendarización de máquinas"
lp.obj.maximize = False

# Es un problema de asignación, el solver ya no debe ser simplex, sino
# algo para programación entera

costos = [[4, 4, 5, 7],
          [6, 7, 5, 6],
          [12, 10, 8, 11]]

tiempos = [[0.3, 0.25, 0.2, 0.2 ],
           [0.2, 0.3, 0.2, 0.25 ],
           [0.8, 0.6, 0.6, 0.5 ]]

horas_disp = [1500, 1200, 1500, 2000]
unidades_req = [4000, 5000, 3000]

# Función objetivo
func_obj = list(chain.from_iterable(costos))

print "función objetivo", func_obj

# Identificación de las restricciones
lp.rows.add(3 + 4)
for elem in range(len(horas_disp)):
    lp.rows[elem].name = "Horas disponibles en máquina {0}".format(elem)
    lp.rows[elem].bounds = 0, horas_disp[elem]
for elem in range(len(unidades_req)):
    lp.rows[4 + elem].name = "Unidades Prod {0} requeridas".format(elem)
    lp.rows[4 + elem].bounds = unidades_req[elem]

# Identificación de las variables
lp.cols.add(3*4)
for c in lp.cols:
    c.name = "P[{0}]M[{1}]".format(c.index / 4 + 1 , c.index % 4 + 1 )
    c.kind = int
    c.bounds = 0.0, None
    print c.name + " " + str(func_obj[c.index])

# Declaración de las restricciones
restricciones = []

# Se empieza con las restricciones de las horas disponibles:
for i in range(len(unidades_req)):
    for j in range(len(horas_disp)):
        restricciones.append((j, i * 4  + j , tiempos[i][j]))

# Terminando por restricciones de unidades requeridas:
for i in range(len(unidades_req)):
    for j in range(len(horas_disp)):
        restricciones.append((i + 4, i * 4 + j, 1.0))

lp.matrix = restricciones

# print "**********"
# for row in lp.rows:
#     print "----------"
#     print row.name
#     # print row.bounds
#     message = " + ".join(
#         [str(r[1])+str(lp.cols[r[0]].name) for r in row.matrix])
#     if row.bounds[0] != row.bounds[1]:
#         message = (str(row.bounds[0]) + " <= " + message + " <= " +
#                    str(row.bounds[1]))
#     else:
#         message += " = "+str(row.bounds[0])
#     print message
# print "**********"

lp.obj[:] = func_obj

solver_list = [lp.intopt, lp.simplex, lp.integer, lp.interior]

for solv in  solver_list:
    print "**********"
    print "=========="
    print "using ", solv
    print "=========="
    solv()

    print "Z = {0}".format(lp.obj.value)
    print "\n".join('{0}[indx:{2}] = {1}'.format(
        c.name, c.primal, c.index) for c in lp.cols)
print "**********"
