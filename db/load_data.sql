INSERT INTO bank (id_bank) VALUES ('1234');
INSERT INTO office (id_office, id_bank) VALUES ('1234','1234');
INSERT INTO client (client_id, age, name, surname) VALUES ('71034506H', 22, 'Roberto', 'de Castro');
INSERT INTO accounts (id_office, id_bank, account_number) VALUES ('1234','1234','0000000000');
INSERT INTO cards (id, client_id, account_id, type, buyLimitDiary, buyLimitMonthly, cashLimitDiary, cashLimitMonthly, commissionEmission, commissionMaintenance, commissionRenovate) VALUES ('1234011234567892','71034506H', '12341234100000000000', 'CREDIT' ,100.00,1000.00,100.00,1000.00,0.0,0.0,0.0);

INSERT INTO comissions (id, description, comission) values(1, 'Comission Credit Emission', 1.00);
INSERT INTO comissions (id, description, comission) values(2, 'Comission Credit Maintenance', 25.00);
INSERT INTO comissions (id, description, comission) values(3, 'Comission Credit Renovate', 30.00);
INSERT INTO comissions (id, description, comission) values(4, 'Comission Debit Emission', 1.00);
INSERT INTO comissions (id, description, comission) values(5, 'Comission Debit Maintenance', 15.00);
INSERT INTO comissions (id, description, comission) values(6, 'Comission Debit Renovate', 20.00);