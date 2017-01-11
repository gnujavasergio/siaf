SELECT
     oficinas.idoficina AS oficinas_idoficina,
     oficinas.nombreoficina AS oficinas_nombreoficina,
     oficinas.observacionoficina AS oficinas_observacionoficina,
     responsables.idresponsable AS responsables_idresponsable,
     responsables.nombreresponsable AS responsables_nombreresponsable,
     responsables.cargoresponsable AS responsables_cargoresponsable
FROM
     oficinas oficinas INNER JOIN responsables responsables ON oficinas.idoficina = responsables.idoficina
WHERE 
$P!{p_condicion}