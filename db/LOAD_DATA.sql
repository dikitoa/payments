--
-- Volcado de datos para la tabla `GENERIC_HANDLER`
--

INSERT INTO `GENERIC_HANDLER` (`id`, `discriminator`) VALUES
('0000', 'BankHandler'),
('0009998786763447', 'CardHandler'),
('1234', 'GenericHandler'),
('1234567890', 'AccountHandler'),
('3452', 'GenericHandler'),
('4444', 'GenericHandler'),
('5432654376658788', 'CardHandler'),
('6666', 'GenericHandler'),
('71557005A', 'PersonHandler'),
('71560136Y', 'PersonHandler'),
('7859656545674570', 'CardHandler'),
('8888888888888888', 'CardHandler'),
('8907345897893570', 'CardHandler'),
('8974095827375549', 'CardHandler'),
('9876', 'OfficeHandler');

--
-- Volcado de datos para la tabla `HISTORY`
--

INSERT INTO `HISTORY` (`history_id`) VALUES
('1234'),
('3452'),
('4444'),
('6666'),
('9876');

--
-- Volcado de datos para la tabla `TRANSACTIONS`
--

INSERT INTO `TRANSACTIONS` (`id`, `amount`, `subject`, `effective_date`, `date`, `extra_information`, `direct_debit_id`, `related`, `sender_account`, `pack`, `operator`, `discriminator`) VALUES
('12345', 899, 'gfghfg', '2014-06-17 01:14:50', '2014-06-11 22:00:00', 'holll', 'jaja', 'olla', '1234567890', 3, 'venga va', 'GenericTransaction');

--
-- Volcado de datos para la tabla `HISTORY_TRANSACTIONS`
--

INSERT INTO `HISTORY_TRANSACTIONS` (`history_id`, `transaction_id`) VALUES
('1234', '12345');

--
-- Volcado de datos para la tabla `ACCOUNTS`
--


INSERT INTO `ACCOUNTS` (`account_number`, `balance`, `last_liquidation`, `liquidation_frequency`, `max_overdraft`, `historyId`, `directDebitHistory`, `failedHistory`) VALUES
('1234567890', 12938, '2014-06-17 01:20:52', 3, 2333, '4444', '6666', '3452');

--
-- Volcado de datos para la tabla `OFFICES`
--

INSERT INTO `OFFICES` (`office_id`, `bank_id`, `local_cost`, `utlities_cost`, `employe_cost`, `total_expenses`, `total_income`, `balance`, `account_number`) VALUES
('9876', '0000', 1000000, 800000, 100000, 1900000, 100000000, 98100000, '1234567890');

--
-- Volcado de datos para la tabla `CLIENTS`
--

INSERT INTO `CLIENTS` (`id`, `name`, `surnames`, `address`, `civil_state`, `phone_number1`, `phone_number2`, `profession`, `birth_date`, `enterprise_name`, `discriminator`, `office`) VALUES
('71557005A', 'Manolo', 'Perico Machado', 'calle s/n', 'c', 666666666, 644342124, 'camionero', '1993-06-18 07:16:10', ' ', 'Person','9876'),
('71560136Y', 'Carlos', 'Mayo de Prado', 'Camino de Santiago 13 24286 HdO', 'd', 657693322, 669696969, 'vividor', '2014-11-10 23:00:00', ' ', 'Person','9876');

--
-- Volcado de datos para la tabla `CARDS`
--

INSERT INTO `CARDS` (`id`, `pin`, `buy_limit_diary`, `buy_limit_monthly`, `cash_limit_diary`, `cash_limit_monthly`, `emission_date`, `expiration_date`, `discriminator`, `cvv`, `fees`, `account_number`, `client_id`, `month_day`, `transaction_list`) VALUES
('0009998786763447', '4612', 400, 1000, 400, 1000, '17/06/2014', '06/17', 'CreditCard', '693', 0, '1234567890', '71557005A', NULL, NULL),
('7859656545674570', '8323', 400, 1000, 400, 1000, '17/06/2014', '06/17', 'DebitCard', '993', 0, '1234567890', '71557005A', NULL, NULL),
('8888888888888888', '5468', 400, 1000, 400, 1000, '17/06/2014', '06/17', 'CreditCard', '711', 0, '1234567890', '71557005A', NULL, NULL),
('8907345897893570', '3332', 400, 1000, 400, 1000, '17/06/2014', '06/17', 'DebitCard', '127', 0, '1234567890', '71557005A', NULL, NULL),
('8974095827375549', '9356', 400, 1000, 400, 1000, '17/06/2014', '06/17', 'DebitCard', '118', 0, '1234567890', '71557005A', NULL, NULL);


