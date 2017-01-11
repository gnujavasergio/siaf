SELECT
     COUNT(activosfijos.idActivoFijo) AS cantidad,
     SUM(activosfijos.costoInicial) AS costoInicial,
     SUM(activosfijos.valor) AS valor,
     SUM(activosfijos.valorNeto) AS valorNeto,
     SUM(activosFijos.depreciacionGestion) AS depreciacionGestion,
     SUM(activosFijos.depreciacionAcumulada) AS depreciacionAcumulada,
     grupos.idGrupo,
     grupos.nombreGrupo,
     grupos.vidaUtilGrupo
FROM
     grupos grupos INNER JOIN auxiliares auxiliares ON grupos.idgrupo = auxiliares.idgrupo
     INNER JOIN activosfijos activosfijos ON auxiliares.idauxiliar = activosfijos.idauxiliar
     INNER JOIN responsables responsables ON activosfijos.idresponsable = responsables.idresponsable
     INNER JOIN oficinas oficinas ON responsables.idoficina = oficinas.idoficina
WHERE
 $P!{p_condicion}
GROUP BY
     grupos.idGrupo,nombreGrupo,vidaUtilGrupo
ORDER BY
     grupos.idGrupo