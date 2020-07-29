<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
    <html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Welcome</title>
        <style>
        table, th, td {
  			border: 1px solid black;
  			border-collapse: collapse;
  			background-color: #f1f1c1;
  			padding:10px;
  			text-align:center;
 
			}
        </style>
        <script type="text/javascript">
        function debit() {
            if (${balance}){
               
            var ask = window.confirm("Are you sure you want to transfer fund of Rs 1000/-?");
            if (ask) {
                window.alert("Rs 1000/- debited from your account.");

                window.location.href = "<c:url value='/transfering' />";

            }
           }
            else{
						window.alert("There is no sufficient balance in your account!");
                }
        }
        </script>
    </head>
    <body>
    <h1 style="text-align:center;">Account Holder Details</h1>
    
        <table style="width:100%;">
        <tr>
            <th>Account Holder</th>
            <th >Account No</th>
            <th>Balance</th>
            <th>Mobile</th>
            <th>City</th>
            <th>State</th>
            <th>Country</th>
            <th>PinCode</th>
        </tr>
           
		<c:forEach var="entry" items="${details}">

        <tr>
           <td>${entry.name}</td>
            <td>${entry.accountNo}</td>
            <td>${entry.balance}</td>
            <td>${entry.phoneno}</td>
            <td>${entry.city}</td>
            <td>${entry.state}</td>
            <td>${entry.country}</td>
            <td>${entry.pincode}</td>
        </tr>
        </c:forEach>
       
        </table >
        <!-- <form action="<c:url value='debit()' />" method="get" id="form1">
     	</form>
		<button type="submit" form="form1" value="Submit">FundTransfer</button> -->
		 <input type="button" value="FundTransfer" onclick="debit()"> 
        <a href="<c:url value='/' />">Home</a>
        <a href="<c:url value='/logout' />">Logout</a>
        <h1 style="text-align:center;padding:20px;"> Last Five Transactions</h1>
        <table style="width:100%;">
        <tr>
            <th>Transaction Id</th>
            <th>Transaction Amount</th>
            <th>Account Holder Id</th>
            <th>Time Stamp</th>
        </tr>
        <c:forEach var="entry" items="${translst}">

        <tr>
       		<td>${entry.id}</td>
       		<td>${entry.debitedAmount}</td>
       		<td>${entry.uid}</td>
       		<td>${entry.updateDateTime}</td>
       
        </tr>
        </c:forEach>
        </table>
    </body>
    </html>