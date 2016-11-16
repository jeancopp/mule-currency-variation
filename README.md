# mule-currency-variation

For using the api, you must access the address http://localhost:8081/eurovariation/init/{init-date}/end/{end-date} after download their.

For get the variation of euro, you need to inform the init-date param and end-date param, like this:
http://localhost:8081/eurovariation/init/2016-11-13/end/2016-11-14

The api will access information about that days and will gone calculate the variation.
The calcule is like this: 
  Variation = end-date cotation / initial-date cotation - 1;

