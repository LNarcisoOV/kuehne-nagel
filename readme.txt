Leonardo Narciso - 14/12/2018.
Kuehne-nagel exercise for IT selection process.


Was created a controller for this microservice. 
This service is a POST and the header is 'application/xml'.
In the requisition's body, is passed a XML sent in email as example.
There's some validations for convert this XML in a Java object.
The link for test this service is this:
http://localhost:8080/kuehne-nagel/sendMessageQueue/


XML:
<UC_STOCK_LEVEL_IFD>
	<CTRL_SEG>
		<TRNNAM>UC_STOCK_LEVEL</TRNNAM>
		<TRNVER>20180100</TRNVER>
		<UUID>0de01919-81eb-4cc7-a51d-15f6085fc1a4</UUID>
		<WH_ID>WHS01</WH_ID>
		<CLIENT_ID>CLIE01</CLIENT_ID>
		<ISO_2_CTRY_NAME>GB</ISO_2_CTRY_NAME>
		<REQUEST_ID>bc2a55e8-5a07-4af6-85fd-8290d3ccfb51</REQUEST_ID>
		<ROUTE_ID>186</ROUTE_ID>
	</CTRL_SEG>
</UC_STOCK_LEVEL_IFD>