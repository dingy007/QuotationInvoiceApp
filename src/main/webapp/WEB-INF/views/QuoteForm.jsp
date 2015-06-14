<%@ include file="header_footer/header.jsp" %>
<!-- <title>Spring - Hibernate Integration - Quotation & Invoice App</title>
</head>
<body>
	<div class="container"> -->
		<h1>Add a New Quote</h1>

		<form:form action="addQuote" method="post" modelAttribute="quote">
		<form:errors/>
			<table>
				<tr>
					<td><spring:message code="label.c_name" /></td>
					<td><form:input path="c_name" size="40" value="CompanyA" /></td>
					<td><form:errors path="c_name" cssClass="fieldErrors" /></td>
				</tr>
				<tr>
					<td><spring:message code="label.c_address" /></td>
					<td><form:input path="c_add" size="40" value="ComapnyA's Address here" /></td>
					<td><form:errors path="c_add" cssClass="fieldErrors" /></td>
				</tr>
				<tr>
					<td><spring:message code="label.c_initials" /></td>
					<td><form:input path="c_initials" size="3" maxlength="3" value="COA" /></td>
					<td><form:errors path="c_initials" cssClass="fieldErrors" /></td>
				</tr>

				<tr>
					<td><spring:message code="label.c_phone" /></td>
					<td><form:input path="c_phone" size="7" value="9123456" /></td>
					<td><form:errors path="c_phone" cssClass="fieldErrors"></form:errors></td>
				</tr>
				<tr>
					<td><spring:message code="label.c_person_in_charge" /></td>
					<td><form:input path="person_in_charge" size="20" value="Comapny_PIC" /></td>
					<td><form:errors path="person_in_charge"
							cssClass="fieldErrors" /></td>
				</tr>
				<tr>
					<td><spring:message code="label.c_amount" /></td>
					<td><form:input path="amount" size="7" value="1200.00" /></td>
					<td><form:errors path="amount" cssClass="fieldErrors"></form:errors></td>
				</tr>
				<tr>
					<td><spring:message code="label.c_sales_person" /></td>
					<td><form:input path="sales_person" size="20" value="Sales PersonA" /></td>
					<td><form:errors path="sales_person" cssClass="fieldErrors"></form:errors></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit"
						value="<spring:message code='label.addQuote'/>" name="btnSubmit" />
						<input type="reset" value="<spring:message code='label.cancel'/>"
						name="btnCancel" /></td>
				</tr>
			</table>
		</form:form>
<%@ include file="header_footer/footer.jsp" %>