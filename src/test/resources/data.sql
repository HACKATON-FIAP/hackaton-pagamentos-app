DELETE FROM db_pagamentos;

INSERT INTO db_pagamentos (id_pagamento, id_carrinho, valor_total, tipo_pagamento)
VALUES (1, '10', 200, 0),
       (2, '11', 300, 1),
       (3, '12', 400, 2);
       (4, '13', 500, 3);