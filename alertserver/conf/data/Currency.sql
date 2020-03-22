DELETE FROM currency;

INSERT INTO currency (id,int_status,changed_by,changed_on,created_by,created_on,description,ext_id,"text",country,decimals,flagicon,peggedcurrency,symbol) VALUES 
(1,0,NULL,NULL,NULL,NULL,'USD','USD','USD','USA',2,'https://image.flaticon.com/icons/svg/197/197374.svg','pegged','$')
,(2,0,NULL,NULL,NULL,NULL,'INR','INR','INR','India',2,'https://image.flaticon.com/icons/svg/197/197419.svg','pegged','₹')
;