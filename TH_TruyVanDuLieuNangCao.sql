select productCode, productName, buyprice, quantityInStock from products
where buyprice > 56.76 and quantityInStock > 10;

select productCode,productName,buyprice,textDescription
from products
inner join productlines
on products.productline = productlines.productline
where buyprice > 56.76 and buyprice < 95.59;

select productCode,productName,buyprice,quantityInStock,productVendor,productline
from products
where productLine = 'Classic Cars' or productVendor = 'Min Lin Diecast';