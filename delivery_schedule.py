#  -*- encoding: utf-8 -*-

"""This script tries to resolve the shipment schedule problem for Two
Depots, Ten Demand Points in Applied Dynamic Programming, Richard
Ernest Bellman, pág 76 [96 pdf]

"""

# De interntar resolver el problema a prueba y error se tiene un
# espacio de soluciones:
# \prod{\nchoosek{demanda_n + 2 - 1}{demanda_n}} = \prod{demanda_n} =
# 1568434323456

import numpy  as np

##########
# DATOS: #
##########

# Deposito 1
g1 = np.array([[ 0, 0, 0],
               [ 0, 1.0, 0],
               [ 1, 2.0, 0],
               [ 0, 3.0, 0.01],
               [ 0, 1.5, 0],
               [ 0, 2.5, 0],
               [ 10, 5.0, -0.01],
               [ 0, 3.0, 0],
               [ 0, 6.0, 0],
               [ 8, 6.0, -0.05],
               [ 0, 6.0, 0]], dtype=float)

# Deposito 2
g2 = np.array([[0, 0, 0],
               [2, 3.1, 0],
               [0, 4.1, 0],
               [0, 2.1, 0],
               [0, 1.1, 0.1],
               [0, 2.6, 0],
               [0, 3.0, 0],
               [5, 1.0, 0.2],
               [0, 2.0, 0],
               [0, 2.0, 0],
               [0, 5.0, 0.01]], dtype=float)

demanda = np.array([0, 10, 25, 45, 15, 5, 15, 20, 15, 10, 20])

f_dict = {}
schedule = {}
opt_schedule_cost = {}

def G1(N, x1):
    """This function evaluates the polynomial return so that the function
    is not verbose

    Parameters
    ----------
    N : int
        The stage of the recursion, ideally we start from the top
    x1 : int
        Resources in deposit 1.

    """
    if x1 != 0:
        return g1[N, 0] + (g1[N, 1] * x1) + g1[N, 2] * (x1 ** 2)
    else:
        return 0

def G2(N, x2):
    """This function evaluates the polynomial return so that the function
    is not verbose

    Parameters
    ----------
    N : int
        The stage of the recursion, ideally we start from the top
    x2 : int
        Resources in deposit 2.

    """
    if x2 != 0:
        return g2[N, 0] + (g2[N, 1] * x2) + g2[N, 2] * (x2 ** 2)
    else:
        return 0

def reconstruct_schedule(N, x1):
    """This function shows the shipment schedule taken.

    Parameters
    ----------
    N : int
        Print shipment schedules for the first N deposits
    x1 :
        Considering x1 amount of resources

    """
    global opt_schedule_cost
    message = "Modificando Depósito {0}; A mover: {1}; se requere: {2}".format(
        N,
        x1 - schedule[(N, x1)][1],
        sum(demanda[:N]))
    opt_schedule_cost[N - 1] = (
        'D1 = ', schedule[(N, x1)][1], 'D2 = ',
        schedule[(N, x1)][2], 'costo = ',
        G1(N, schedule[(N, x1)][1]) + G2(N, schedule[(N, x1)][2]))
    # print opt_schedule_cost[N-1]

    if N > 1:
        reconstruct_schedule(N-1, x1 - schedule[(N, x1)][1])
    print schedule[(N, x1)][0], '|  ', G1(N, schedule[(N, x1)][1]) + G2(N, schedule[(N, x1)][2])


def F(N, x1):
    """This function implements the recursion needed for the dynamical
    programming solution of this problem.

    Parameters
    ----------
    N : int
        Stage in the recursion, ideally we start from the top.
    x1 : int
        Resources in deposit 1.
    x2 : int
        Resources in deposit 2.

    """

    global f_dict
    if (N, x1) not in f_dict:
        if N == 1:
            f_dict[(N, x1)] = G1(N, x1) + G2(N, demanda[N] - x1)
            schedule[(N, x1)] = ("PD = {0}, Dep 1 = {1}, Dep 2 = {2}".format(
                N, x1, demanda[N] - x1), x1, demanda[N] - x1)
        else:
            rango_var_xN1 = range(max(0, x1 - sum(demanda[:N])),
                                  min(x1, demanda[N]) + 1)

            val_utilizar = [ (F(N-1, x1 - xN1) +
                              G1(N, xN1) + G2(N, demanda[N] - xN1))
                             for xN1 in rango_var_xN1]

            f_dict[(N, x1)] = min(val_utilizar)
            ind = val_utilizar.index(f_dict[(N, x1)])

            x_N1 = rango_var_xN1[ind]
            x_N2 = demanda[N] - x_N1
            schedule[(N, x1)] = ("PD = {0}, Dep 1 = {1}, Dep 2 = {2}".format(
                N, rango_var_xN1[ind], demanda[N] - rango_var_xN1[ind]),
                                 x_N1,
                                 x_N2)

    return f_dict[(N, x1)]

# Los siguientes comandos son bajo la sugerencia del libro, sin
# embargo no veo la necesidad de utilizarlos
# costos_totales = [F(10, i) for i in range(sum(demanda) + 1)]
# costo_min = min(costos_totales)
# print costos_totales.index(costo_min), costo_min

print "F(10, 100) = ", F(10, 100)
reconstruct_schedule(10, 100)

# print "Se calcularon " + str(len(schedule)) + " puntos"
# for k in opt_schedule_cost.keys():
#     print "Punto de Demanda {0}: D1 = {1}, D2 = {2}; Costo ="
#     "{3}".format(
#         k + 1,
#         opt_schedule_cost[k][1],
#         opt_schedule_cost[k][3],
#         opt_schedule_cost[k][5])

# Se supone que se envían 100 unidades del depósito 1 y 80 del depósito 2.

# La solución óptima reportada en el libro es

# | Demanda | Al PD | De Dep 1 | De Dep 2 |  Costo | Costo Acum |
# |---------+-------+----------+----------+--------+------------|
# |      10 |     1 |       10 |        0 |  10.00 |      10.00 |
# |      25 |     2 |       25 |        0 |  51.00 |        61. |
# |      45 |     3 |        5 |       40 |  99.25 |     160.25 |
# |      15 |     4 |       15 |        0 |  22.50 |     182.75 |
# |       5 |     5 |        5 |        0 |  12.50 |     195.25 |
# |      15 |     6 |        0 |       15 |  45.00 |     240.25 |
# |      20 |     7 |       20 |        0 |  60.00 |     300.25 |
# |      15 |     8 |        0 |       15 |  30.00 |     330.25 |
# |      10 |     9 |        0 |       10 |  20.00 |     350.25 |
# |      20 |    10 |       20 |        0 | 120.00 |     470.25 |
