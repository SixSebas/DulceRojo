-- ============================================================
-- Seed data para DulceRojo - SC-403
-- ============================================================
-- Pasos para ejecutar:
--   1) CREATE DATABASE dulcerojodb CHARACTER SET utf8mb4;
--   2) Arrancar la app una vez para que Hibernate cree las tablas
--   3) Ejecutar este script en MySQL Workbench
--   4) Registrar un usuario desde /registro y luego en Workbench:
--      UPDATE usuarios SET rol = 'ROLE_ADMIN' WHERE correo = 'tu@correo.com';
-- ============================================================

USE dulcerojodb;

INSERT INTO productos (nombre, descripcion, precio, stock, categoria, imagen) VALUES
('Choco Chips Clásica', 'Galleta gruesa con chispas de chocolate semiamargo.', 2000, 15, 'Clásicas', null),
('Red Velvet Rellena', 'Masa red velvet rellena de queso crema dulce.', 2500, 8, 'Rellenas', null),
('Doble Chocolate', 'Masa de cacao con chunks de chocolate blanco.', 2200, 3, 'Clásicas', null),
('Edición Navidad', 'Galleta de canela con glasé rojo y verde.', 3000, 0, 'Edición Especial', null),
('Mantequilla de Maní', 'Suave y crocante, rellena de maní tostado.', 2300, 12, 'Clásicas', null);

SELECT * FROM productos;
SELECT * FROM usuarios;