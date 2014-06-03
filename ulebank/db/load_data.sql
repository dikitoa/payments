INSERT INTO bank (id_bank) VALUES ('1234');
INSERT INTO office (id_office, id_bank) VALUES ('1234','1234');
INSERT INTO client (client_id, age, name, surname) VALUES ('71034506H', 22, 'Roberto', 'de Castro');
INSERT INTO accounts (account_number) VALUES ('12341234100000000000');
INSERT INTO cards (id, client_id, account_id, type, buyLimitDiary, buyLimitMonthly, cashLimitDiary, cashLimitMonthly, commissionEmission, commissionMaintenance, commissionRenovate) VALUES ('123401123456789','71034506H', '12341234100000000000', 'CREDIT' ,100.00,1000.00,100.00,1000.00,0.0,0.0,0.0);