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
        <h1 class="page-announce-text valign">Liste des membres</h1>
      </div>
      <div class="row">
	    <div class="col s12">
	      <table class="striped no-padding">
            <thead>
              <tr>
                <th>Nom</th>
                <th>Prenom</th>
                <th class="hide-on-small-only">Adresse</th>
                <th class="hide-on-small-only">E-mail</th>
                <th class="hide-on-small-only">Telephone</th>
                <th>Details</th>
              </tr>
            </thead>
            <tbody>
              <c:if test="${! empty memberListJSP }">
                <c:forEach var="member" items="${memberListJSP}">
                  <tr>
                    <td><c:out value="${member.getLastname()}"/></td>
                    <td><c:out value="${member.getName()}"/></td>
                    <td><c:out value="${member.getAddress()}"/></td>
                    <td><c:out value="${member.getEmail()}"/></td>
                    <td><c:out value="${member.getPhone()}"/></td>
                    <td class="center"><a href="membre_details?id=<c:out value="${member.getId()}"/>"><ion-icon class="details" name="information-circle-outline"></ion-icon></a></td>
                  </tr>
                </c:forEach>
              </c:if>
              <!-- TODO : parcourir la liste des membres et les afficher selon la structure d'exemple ci-dessus -->
            </tbody>
          </table>
        </div>
      </div>
    </section>
  </main>
  <jsp:include page='footer.jsp'></jsp:include>
</body>
</html>
