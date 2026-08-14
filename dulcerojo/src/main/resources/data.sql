-- ===========================
-- Limpiar datos
-- ===========================

DELETE FROM detalle_pedido;
DELETE FROM pedidos;
DELETE FROM productos;
DELETE FROM categorias;

ALTER TABLE detalle_pedido AUTO_INCREMENT = 1;
ALTER TABLE pedidos AUTO_INCREMENT = 1;
ALTER TABLE productos AUTO_INCREMENT = 1;
ALTER TABLE categorias AUTO_INCREMENT = 1;

-- ===========================
-- Categorías
-- ===========================

INSERT INTO categorias (nombre, descripcion) VALUES
('Clásicas', 'Galletas tradicionales.'),
('Rellenas', 'Galletas con relleno.'),
('Edición Especial', 'Productos por temporada.'),
('Premium', 'Productos especiales.');

-- ===========================
-- Productos
-- ===========================

INSERT INTO productos
(nombre, descripcion, precio, stock, categoria_id, imagen)
VALUES

(
'Choco Chips Clásica',
'Galleta con chispas de chocolate.',
2000,
15,
(SELECT id FROM categorias WHERE nombre='Clásicas'),
NULL
),

(
'Red Velvet Rellena',
'Rellena de queso crema.',
2500,
8,
(SELECT id FROM categorias WHERE nombre='Rellenas'),
NULL
),

(
'Doble Chocolate',
'Chocolate blanco y cacao.',
2200,
3,
(SELECT id FROM categorias WHERE nombre='Clásicas'),
NULL
),

(
'Edición Navidad',
'Galleta decorada para Navidad.',
3000,
0,
(SELECT id FROM categorias WHERE nombre='Edición Especial'),
NULL
),

(
'Mantequilla de Maní',
'Con maní tostado.',
2300,
12,
(SELECT id FROM categorias WHERE nombre='Clásicas'),
NULL
),

(
'Chocolate Blanco y Macadamia',
'Galleta premium.',
3200,
10,
(SELECT id FROM categorias WHERE nombre='Premium'),
NULL
);