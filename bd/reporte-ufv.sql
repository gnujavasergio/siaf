SELECT
     configuraciones.ufv AS configuraciones_ufv,
     configuraciones.fechaufv AS configuraciones_fechaufv,
     configuraciones.idconfiguracion AS configuraciones_idconfiguracion
FROM
     configuraciones configuraciones
WHERE
  configuraciones.fechaufv >= $P{p_start} AND configuraciones.fechaufv <= $P{p_end}
ORDER BY
     configuraciones.idConfiguracion
