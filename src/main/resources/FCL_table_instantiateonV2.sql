-- drop tables -----------------------------------
--drop table if exists transactions;
--drop table if exists wallet_coins;
--drop table if exists coins;
--drop table if exists wallets;
--drop table if exists league_users;
--drop table if exists leagues;
--drop table if exists users;

-- create tables ----------------------------------

--select * from users;
--
--create table users (
--	id varchar,
--	email varchar unique not null check(email <> ''),
--	username varchar unique not null check(username <> ''),
--	password varchar not null check(password <> ''),
--	date_created timestamp not null,
--	
--	constraint pk_user_id
--	primary key (id)
--);
--create table leagues (
--	league_id varchar,
--	creator_id varchar,
--	league_name varchar unique not null check(league_name <> ''),
--	password varchar check(password <> ''),
--	initial_bal numeric not null check(initial_bal > 0),
--	
--	constraint pk_league_id
--	primary key (league_id),
--	
--	constraint fk_league_creator_id
--	foreign key (creator_id) 
--	references users (id)
--);
--
--create table league_users (
--	user_id varchar,
--	league_id varchar,
--	
--	constraint pk_league_users
--	primary key (user_id, league_id),
--	
--	constraint fk_league_users_user
--	foreign key (user_id)
--	references users (id),
--	
--	constraint fk_league_users_league
--	foreign key (league_id)
--	references leagues (league_id)
--);
--
--create table wallets (
--	wallet_id varchar,
--	user_id varchar,
--	league_id varchar,
--	initial_bal numeric not null check(initial_bal > 0),
--	wallet_bal numeric not null check(wallet_bal >= 0),
--	cash_bal numeric not null,
--	
--	constraint pk_wallet_id
--	primary key (wallet_id),
--	
--	constraint fk_wallet_user_id
--	foreign key (user_id)
--	references users (id),
--	
--	constraint fk_wallet_league_id
--	foreign key (league_id)
--	references leagues (league_id)
--);
--

--
----create table wallet_coins (
----	walletcoin_id varchar,
----	wallet_id varchar,
----	coin_id varchar,
----	amount numeric not null check(amount > 0),
----	
----	constraint pk_wallet_coins_id
----	primary key (walletcoin_id),
----	
----	constraint fk_wallet_coins_wallet_id
----	foreign key (wallet_id)
----	references wallets (wallet_id),
----	
----	constraint fk_wallet_coins_coin_id
----	foreign key (coin_id)
----	references coins (coin_id)
----);
--
--create table transactions (
--	transaction_id varchar,
--	wallet_id varchar,
--	coin_id varchar,
--	usd_change numeric not null,
--	coin_change numeric not null,
--	date_time timestamp not null,
--	
--	constraint pk_transaction_id
--	primary key (transaction_id),
--	
--	constraint fk_transaction_wallet_id
--	foreign key (wallet_id)
--	references wallets (wallet_id),
--	
--	constraint fk_transaction_coin_id
--	foreign key (coin_id)
--	references coins (coin_id)
--);

-- insert users ------------------------------
insert into users (id, email, username, password, date_created)
values ('05588560-620d-41a8-90ac-ba54a5bebe5e', 'real@email.real', 'murphy', 'p4ssw0rd', current_timestamp);

insert into users (id, email, username, password, date_created)
values ('f9dd01cb-bd69-4b9b-82d5-8daee84e189e', 'soreal@email.real', 'Carol', 'p4ssw0rd', current_timestamp);

insert into users (id, email, username, password, date_created)
values ('35521b40-aff0-43cb-9d2b-1f6d22b363ce', 'real1@email.real', 'Steve', 'p4ssw0rd', current_timestamp);

insert into users (id, email, username, password, date_created)
values ('08f25545-8c6d-4724-874f-64f2560971ab', 'rea1@email.real', 'Bill', 'p4ssw0rd', current_timestamp);

insert into users (id, email, username, password, date_created)
values ('2c92188f-835e-48a9-9728-2dbbe3e12ad5', 'really@email.real', 'John', 'p4ssw0rd', current_timestamp);


-- insert leagues --------------------------------------
--insert into leagues (league_id, creator_id, league_name, password, initial_bal)
--values ('68ac165c-fd96-41c3-bdee-682e35c267da', '05588560-620d-41a8-90ac-ba54a5bebe5e', 'trading league name', null, 1000);
--
--insert into leagues (league_id, creator_id, league_name, password, initial_bal)
--values ('798d8ed7-8352-4598-89b2-7426ae4f567b', 'f9dd01cb-bd69-4b9b-82d5-8daee84e189e', 'the big kahunas', 'secretpassword', 10000);

-- insert league users ---------------------------------
--insert into league_users (user_id, league_id)
--values ('05588560-620d-41a8-90ac-ba54a5bebe5e', '68ac165c-fd96-41c3-bdee-682e35c267da');
--
--insert into league_users (user_id, league_id)
--values ('08f25545-8c6d-4724-874f-64f2560971ab', '68ac165c-fd96-41c3-bdee-682e35c267da');
--
--insert into league_users (user_id, league_id)
--values ('2c92188f-835e-48a9-9728-2dbbe3e12ad5', '68ac165c-fd96-41c3-bdee-682e35c267da');
--
--
--insert into league_users (user_id, league_id)
--values ('f9dd01cb-bd69-4b9b-82d5-8daee84e189e', '798d8ed7-8352-4598-89b2-7426ae4f567b');
--
--insert into league_users (user_id, league_id)
--values ('35521b40-aff0-43cb-9d2b-1f6d22b363ce', '798d8ed7-8352-4598-89b2-7426ae4f567b');
--
--insert into league_users (user_id, league_id)
--values ('2c92188f-835e-48a9-9728-2dbbe3e12ad5', '798d8ed7-8352-4598-89b2-7426ae4f567b');

-- insert wallets -------------------------------------
--insert into wallets (wallet_id, user_id, league_id, initial_bal, wallet_bal, cash_bal)
--values ('1cad163f-847f-4c76-9b36-30a7efea706e', '05588560-620d-41a8-90ac-ba54a5bebe5e', '68ac165c-fd96-41c3-bdee-682e35c267da', 1000, 0.0, 1000);
--
--insert into wallets (wallet_id, user_id, league_id, initial_bal, wallet_bal, cash_bal)
--values ('5b35b6de-f46b-452c-9703-a2f86cc15012', '08f25545-8c6d-4724-874f-64f2560971ab', '68ac165c-fd96-41c3-bdee-682e35c267da', 1000, 0.0, 1000);
--
--insert into wallets (wallet_id, user_id, league_id, initial_bal, wallet_bal, cash_bal)
--values ('86f1859d-1254-4d54-bcd2-5ce4d3fed54e', '2c92188f-835e-48a9-9728-2dbbe3e12ad5', '68ac165c-fd96-41c3-bdee-682e35c267da', 1000, 0.0, 1000);
--
--
--insert into wallets (wallet_id, user_id, league_id, initial_bal, wallet_bal, cash_bal)
--values ('2d7706cd-f930-44cb-a337-1611f63e6cda', 'f9dd01cb-bd69-4b9b-82d5-8daee84e189e', '798d8ed7-8352-4598-89b2-7426ae4f567b', 10000, 0.0, 10000);
--
--insert into wallets (wallet_id, user_id, league_id, initial_bal, wallet_bal, cash_bal)
--values ('0b431b54-f10f-441f-b349-040190ef3326', '35521b40-aff0-43cb-9d2b-1f6d22b363ce', '798d8ed7-8352-4598-89b2-7426ae4f567b', 10000, 0.0, 10000);
--
--insert into wallets (wallet_id, user_id, league_id, initial_bal, wallet_bal, cash_bal)
--values ('e1d79da7-703e-4b23-abc8-9723a3927ac6', '2c92188f-835e-48a9-9728-2dbbe3e12ad5', '798d8ed7-8352-4598-89b2-7426ae4f567b', 10000, 0.0, 10000);

-- insert coins ----------------------------------------

select * from coins

insert into coins (coin_id, wallet_id, curr_pair, amount)
values ('0', '9337c66c-7b0e-4890-91ff-571050f2ca77', 'BTC-USD', 0.005);

insert into coins (coin_id, wallet_id, curr_pair, amount)
values ('1', '985a4fd6-2082-4588-8bbd-07503f41100f', 'SHIB-USD', 90000);

insert into coins (coin_id, wallet_id,   curr_pair, amount)
values ('2', 'c91b7d0c-fc8f-41a9-8601-2351f2a87448', 'BTC-USD', 0.005);

insert into coins (coin_id, wallet_id,   curr_pair, amount)
values ('3', '4ccd1545-dcd8-4818-964f-18f9d34c750f', 'ETH-USD', 0.05);

insert into coins (coin_id, wallet_id,   curr_pair, amount)
values ('4', 'f4ce9098-629e-4170-b269-f5aaad12b5f2', 'BTC-USD', 0.005);


select * from users;
select * from coins;

-- perform transactions ---------------------------------
-- murphy_trading_league_name_wallet --------------------
--insert into wallet_coins (walletcoin_id, wallet_id, coin_id, amount)
--values ('7d78bf55-683c-48eb-805b-13471588d3ea', '1cad163f-847f-4c76-9b36-30a7efea706e', '9337c66c-7b0e-4890-91ff-571050f2ca77', 0.01);
--
--insert into transactions (transaction_id, wallet_id, coin_id, usd_change, coin_change, date_time)
--values ('873f573c-8af2-4ac6-baf8-ca2c2f41f601', '1cad163f-847f-4c76-9b36-30a7efea706e', '9337c66c-7b0e-4890-91ff-571050f2ca77', -600, 0.01, current_timestamp);
--
--update wallets 
--set wallet_bal = 600, cash_bal = 400
--where wallet_id = '1cad163f-847f-4c76-9b36-30a7efea706e';
--
--
--insert into wallet_coins (walletcoin_id, wallet_id, coin_id, amount)
--values ('bfc0fb88-3342-4b7a-8249-7dba0609b35d', '1cad163f-847f-4c76-9b36-30a7efea706e', 'c91b7d0c-fc8f-41a9-8601-2351f2a87448', 10000);
--
--insert into transactions (transaction_id, wallet_id, coin_id, usd_change, coin_change, date_time)
--values ('ec11b2dc-9c66-4f34-8d13-919b2e9c0200', '1cad163f-847f-4c76-9b36-30a7efea706e', 'c91b7d0c-fc8f-41a9-8601-2351f2a87448', -0.50, 10000, current_timestamp);
--
--update wallets 
--set wallet_bal = 600.50, cash_bal = 399.50
--where wallet_id = '1cad163f-847f-4c76-9b36-30a7efea706e';
--
--
--insert into wallet_coins (walletcoin_id, wallet_id, coin_id, amount)
--values ('2ea2d44d-b073-4081-bd89-597d51e6c868', '1cad163f-847f-4c76-9b36-30a7efea706e', 'f4ce9098-629e-4170-b269-f5aaad12b5f2', 300);
--
--insert into transactions (transaction_id, wallet_id, coin_id, usd_change, coin_change, date_time)
--values ('0af5bb01-dc18-49bb-b3f9-438aafec853c', '1cad163f-847f-4c76-9b36-30a7efea706e', 'f4ce9098-629e-4170-b269-f5aaad12b5f2', -300, 300, current_timestamp);
--
--update wallets 
--set wallet_bal = 900.50, cash_bal = 99.50
--where wallet_id = '1cad163f-847f-4c76-9b36-30a7efea706e';
--
--
--select * from wallet_coins wc 
--
--create table coins
create table coins (
	wallet_id varchar,
	curr_pair varchar not null check(curr_pair <> ''),
	amount varchar not null check(amount <> ''),
	
	constraint pk_wallet_id--_curr_pair
	primary key (wallet_id)--, curr_pair)
--	
--	constraint fk_curr_pair
--	foreign key (curr_pair),
);
--drop table coins;

-- insert coins ----------------------------------------
insert into coins (wallet_id, curr_pair, amount)
values ('9337c66c-7b0e-4890-91ff-571050f2ca77', 'BTC-USD', 0.005);

insert into coins ( wallet_id, curr_pair, amount)
values ('985a4fd6-2082-4588-8bbd-07503f41100f', 'SHIB-USD', 90000);

insert into coins ( wallet_id,   curr_pair, amount)
values ('c91b7d0c-fc8f-41a9-8601-2351f2a87448', 'BTC-USD', 0.005);

insert into coins (wallet_id,   curr_pair, amount)
values ('4ccd1545-dcd8-4818-964f-18f9d34c750f', 'ETH-USD', 0.05);

insert into coins (wallet_id,   curr_pair, amount)
values ('f4ce9098-629e-4170-b269-f5aaad12b5f2', 'BTC-USD', 0.005);


select wallet_id from coins where wallet_id = 'f4ce9098-629e-4170-b269-f5aaad12b5f2'

select * from users;
select * from coins;









