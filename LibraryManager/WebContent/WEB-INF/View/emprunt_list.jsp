<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Library Management</title>
  <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
  <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/css/materialize.min.css">
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link href="assets/css/custom.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <jsp:include page='menu.jsp'></jsp:include>
  <main>
    <section class="content">
      <div class="page-announce valign-wrapper">
        <a href="#" data-activates="slide-out" class="button-collapse valign hide-on-large-only"><i class="material-icons">menu</i></a>
        <h1 class="page-announce-text valign">Liste des emprunts</h1>
      </div>

      <c:if test="${! empty errorMessage}">
        <div>
          <p align="center"><c:out value="${errorMessage}"/></p>
        </div>
      </c:if>

      <div class="row">
        <div class="container">
	        <div class="col s12">
	          <table class="striped">
                <thead>
                    <tr>
                        <th>Livre</th>
                        <th>Membre emprunteur</th>
                        <th>Date d'emprunt</th>
                        <th>Retour</th>
                    </tr>
                </thead>
                <tbody id="results">
                    <c:if test="${! empty lendingListJSP}">
                      <c:forEach var="loan" items="${lendingListJSP}">
                        <tr>
                          <td><c:out value="${loan.getBook().getTitle()}" />, <em><c:out value="${loan.getBook().getAuthor()}" /></em></td>
                          <td><c:out value="${loan.getMember().getName()}" /> <c:out value="${loan.getMember().getLastname()}" /></td>
                          <td><c:out value="${loan.getLendDate()}" /></td>
                          <td>
                            <c:if test="${loan.getReturnDate() == null}">
                              <a href="emprunt_return?id=${loan.getId()}">edit<ion-icon class="table-item" name="log-in"></a>
                            </c:if>
                            <c:if test="${loan.getReturnDate() != null}">
                              <c:out value="${loan.getReturnDate()}" />
                            </c:if>
                          </td>
                        </tr>
                      </c:forEach>
                    </c:if>
					 <!-- TODO : parcourir la liste des emprunts en cours et les afficher selon la structure d'exemple ci-dessus -->
					 <!-- TODO : dans le champ "retour", afficher la date de retour si elle existe, et un lien vers la page de retour si la date est vide (comme dans l'exemple ci-dessus) -->
                </tbody>
            </table>
          </div>
        </div>
      </div>
    </section>
  </main>
  <jsp:include page='footer.jsp'></jsp:include>
</body>
</html>
