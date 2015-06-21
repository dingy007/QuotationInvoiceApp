<%@ include file="header_footer/header.jsp" %>
<h1>My Contact List</h1>
	<c:if test="${! empty quoteList}">
		<table>
			<tr>
				<th>Id</th>
				<th>Company Name</th>
				<th>Company Initials</th>
				<th>Person In Charge</th>
				<th>Total amount</th>
				
			</tr>
			<c:forEach items="${quoteList}" var="quote">
				<tr>
					<td>${quote.id}</td>
					<td>${quote.c_name}</td>
					<td>${quote.c_initials }</td>
					<td>${quote.person_in_charge}</td>
					<td>${quote.amount}</td>
					<td><a href="delete/${quote.id}">Delete</a></td>
				</tr>		
			</c:forEach>
		</table>
	</c:if>
<%@ include file="header_footer/footer.jsp" %>