<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Test working with dwr</title>
        <link rel="stylesheet" type="text/css" href="/ajaxdwrsample/styles/autocomplete.css"/>

        <script type='text/javascript' src='/ajaxdwrsample/dwr/interface/AddressServiceFacade.js'></script>
        <script type='text/javascript' src='/ajaxdwrsample/dwr/engine.js'></script>
        <script type='text/javascript' src='/ajaxdwrsample/dwr/util.js'></script>
        <script type="text/javascript" src="/ajaxdwrsample/scripts/prototype/prototype.js"></script>
        <script type="text/javascript" src="/ajaxdwrsample/scripts/script.aculo.us/effects.js"></script>
        <script type="text/javascript" src="/ajaxdwrsample/scripts/script.aculo.us/controls.js"></script>
        <script type="text/javascript" src="/ajaxdwrsample/scripts/autocomplete.js"></script>

        <script type="text/javascript">
            function updateList(autocompleter, token) {
	            AddressServiceFacade.findCompletePostalCodes(token, function(data) { autocompleter.setChoices(data) });
            }
            function nameValueSelector(tag){
    	        return tag;
            }
			function handleAddClick() {
				var searchString = DWRUtil.getValue('searchPostalCode');
				AddressServiceFacade.findAddressByPostalCode(fillAddress,searchString);
			}
			function fillAddress(anAddress) {
	            formAddress = anAddress;
	            DWRUtil.setValues(anAddress);
			}
        </script>
    </head>
    <body>
        <div id="ac">
            <h1>Auto complete using DWR &amp; Spring</h1>
            <input id="searchPostalCode" type="text" />
            <input type="button" value="Search" onclick="handleAddClick();"/>
            <div id="postalCodeList" class="auto_complete"></div>
            <script type="text/javascript">
                new Autocompleter.DWR('searchPostalCode', 'postalCodeList', updateList,{ valueSelector: nameValueSelector, partialChars: 0 });
            </script>
        </div>
		<div>
            <h2>Address</h2>
            <span id="streetname"></span>&nbsp;<span id="houseNumber"></span><br/>
            <span id="postalCode"></span><br/>
            <span id="city"></span><br/>
		</div>
    </body>
</html>