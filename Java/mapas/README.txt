Los mapas deben ser guardados en la siguiente carpeta:
/resources/Mapas

Posición de la matriz a la que corresponde cada mapa:
Zona 0:
0,0 -> extensión_cueva
0,1 -> cueva_inicio
0,2 -> fuera_casa_inicio
0,3 -> antes_cueva_incio
0,-1 -> casa_inicio

Zona 1:
1,0 -> dungeon_desierto
1,1 -> ciudad
1,2 -> mirador
1,3 -> ruta_desierto
1,-1 -> catedral

Zona 2:
2,0 -> dungeon_piso1
2,1 -> dungeon_piso2
2,2 -> dungeon_piso3
2,3 -> pueblo
2,4 -> ruta_dungeon
2,-1 -> ayuntamiento
2,-2 -> casa_pueblo
2,-3 -> cueva_pueblo
2,-4 -> hospital
2,-5 -> residencia

Zona 3:
3,0 -> cueva_antes_dng_final
3,1 -> dng_final
3,2 -> ruta_dng_cueva_final
3,3 -> ruta_dng_final

Para comenzar en un mapa determiando:

Ir a la clase mundo -> busacar el método initMapa() -> poner en cambiarMundo()
los valores de la matriz que correspondan al mapa en que quieres aparecer -->

Ejemplo: quiero aparecer en el mapa antes_cueva_incio, pongo cambiarMundo(0, 2)

--> ir a la clase juego -> buscar el método init() ->
-> en Lucia = new Lucia(x, y) ajustar las coordenadas x e y para aparecer fuera
de las zonas de colisión del mapa 