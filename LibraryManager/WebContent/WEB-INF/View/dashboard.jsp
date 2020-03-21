
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List" %>
<%@ page import="com.app.model.Lending" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
  
<%! private int countOfMembersJSP = -1;%>
<% countOfMembersJSP = (int) request.getAttribute("countOfMembersJSP");%>
<%! private int countOfBooksJSP = -1;%>
<% countOfBooksJSP = (int) request.getAttribute("countOfBooksJSP");%>
<%! private int countOfLoansJSP = -1;%>
<% countOfLoansJSP = (int) request.getAttribute("countOfLoansJSP");%>
<%! private List<Loan> currentLoansJSP = new ArrayList<>();%>
<% currentLoansJSP = (List) request.getAttribute("currentLoansJSP"); %>

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
        <h1 class="page-announce-text valign">Tableau de bord</h1>
      </div>
      <div class="row">
        <div class="col l4 s6">
          <div class="small-box bg-aqua">
            <div class="inner">
              <h3>countOfMembersJSP</h3> <!-- TODO : afficher le nombre de membres � la place de 12 -->
              <p>Membres</p>
            </div>
            <div class="icon">
            <ion-icon name="people"></ion-icon>
            </div>
            <a href="membre_list" class="small-box-footer" class="animsition-link">Liste des membres <i class="fa fa-arrow-circle-right"></i></a>
          </div>
        </div>
        <div class="col l4 s6">
          <div class="small-box bg-green">
            <div class="inner">
              <h3>countOfBooksJSP</h3> <!-- TODO : afficher le nombre de livres � la place de 27 -->
              <p>Livres</p>
            </div>
            <div class="icon">
              <ion-icon name="book"></ion-icon>
            </div>
            <a href="livre_list" class="small-box-footer" class="animsition-link">Liste des livres <i class="fa fa-arrow-circle-right"></i></a>
          </div>
        </div>
        <div class="col l4 s6">
          <div class="small-box bg-yellow">
            <div class="inner">
              <h3>countOfLoansJSP</h3> <!-- TODO : afficher le nombre d'emprunts � la place de 1515 -->
              <p>Emprunts</p>
            </div>
            <div class="icon">
              <ion-icon name="bookmarks"></ion-icon>
            </div>
            <a href="emprunt_list" class="small-box-footer" class="animsition-link">Liste des emprunts <i class="fa fa-arrow-circle-right"></i></a>
          </div>
        </div>
        <div class="container">
	        <div class="col s12">
	          <h5>Emprunts en cours</h5>
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
                  <c:forEach items="${currentLoansJSP}" var="loan">
                    <tr>
                      <td><%= loan.getBook().getTitle() %>, <em><%= loan.getBook().getAuthor() %></em></td>
                      <td><%= loan.getMember().getFirstName() %> <%= loan.getMember().getLastName() %></td>
                      <td><%= loan.getLoanDate() %></td>
                      <td><a href='emprunt_return?id=<%= loan.getId() %>'><ion-icon class="table-item" name="log-in"></a></td>													
                    </tr>
                  </c:forEach>
                     <!-- TODO : parcourir la liste des emprunts en cours et les afficher selon la structure d'exemple ci-dessus -->
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
