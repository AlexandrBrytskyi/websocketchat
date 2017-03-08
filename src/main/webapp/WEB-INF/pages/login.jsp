<%@include file="include.jsp" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- CSS -->
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
    <link rel="stylesheet" href="../../assets/login/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../assets/login/assets/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="../../assets/login/assets/css/form-elements.css">
    <link rel="stylesheet" href="../../assets/login/assets/css/style.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- Favicon and touch icons -->
    <link rel="shortcut icon" href="assets/ico/favicon.png">
    <link rel="apple-touch-icon-precomposed" sizes="144x144"
          href="../../assets/login/assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114"
          href="../../assets/login/assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72"
          href="../../assets/login/assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="../../assets/login/assets/ico/apple-touch-icon-57-precomposed.png">


    <%--scripts--%>
    <script src="../../assets/login/assets/js/jquery-1.11.1.min.js"></script>
    <script src="../../assets/login/assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="../../assets/login/assets/js/scripts.js"></script>


    <%--<script>--%>
    <%--<%@ include file="../../assets/js/signin.js"%>--%>
    <%--</script>--%>

    <title>Login</title>


</head>

<body>


<!-- Top content -->
<div class="top-content">

    <div class="inner-bg">
        <div class="container">

            <div class="row">
                <div class="col-sm-8 col-sm-offset-2 text">
                    <h1>Welocome to Web Socket with Ajax sample chat</h1>
                    <div class="description">
                        <p>
                            This application is written just to learn Web sockets with Ajax
                        </p>
                        <p>
                            You can write on <a href="brytskyiolexandr1995@gmail.com" target="_blank">brytskyiolexandr1995@gmail.com</a>
                        </p>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-sm-5">

                    <div class="form-box">
                        <div class="form-top">
                            <div class="form-top-left">
                                <h3>Login to chat</h3>
                                <p>Enter username and password to log on:</p>
                            </div>
                            <div class="form-top-right">
                                <i class="fa fa-key"></i>
                            </div>
                        </div>
                        <div class="form-bottom">
                            <form role="form" action="/chat/login" method="post" class="login-form">
                                <div class="form-group">
                                    <label class="sr-only" for="form-username">Username</label>
                                    <input type="text" name="username" placeholder="Username..."
                                           class="form-username form-control" id="form-username">
                                </div>
                                <div class="form-group">
                                    <label class="sr-only" for="form-password">Password</label>
                                    <input type="password" name="password" placeholder="Password..."
                                           class="form-password form-control" id="form-password">
                                </div>

                                <input type="hidden" name="${_csrf.parameterName}"
                                       value="${_csrf.token}" />

                                <button type="submit" class="btn">Sign in!</button>
                            </form>
                        </div>
                    </div>


                    <%--in addition must be added--%>
                    <%--  <div class="social-login">
                          <h3>...or login with:</h3>
                          <div class="social-login-buttons">
                              <a class="btn btn-link-1 btn-link-1-facebook" href="#">
                                  <i class="fa fa-facebook"></i> Facebook
                              </a>
                              <a class="btn btn-link-1 btn-link-1-twitter" href="#">
                                  <i class="fa fa-twitter"></i> Twitter
                              </a>
                              <a class="btn btn-link-1 btn-link-1-google-plus" href="#">
                                  <i class="fa fa-google-plus"></i> Google Plus
                              </a>
                          </div>
                      </div>--%>


                </div>

                <div class="col-sm-1 middle-border"></div>
                <div class="col-sm-1"></div>

                <div class="col-sm-5">

                    <div class="form-box">
                        <div class="form-top">
                            <div class="form-top-left">
                                <h3>Sign up now</h3>
                                <p>Fill in the form below to get instant access:</p>
                            </div>
                            <div class="form-top-right">
                                <i class="fa fa-pencil"></i>
                            </div>
                        </div>
                        <div class="form-bottom">
                            <form role="form" action="/chat/signup" method="post" class="registration-form">
                                <div class="form-group">
                                    <label class="sr-only" for="form-first-name">User name</label>
                                    <input type="text" name="username" placeholder="User name..."
                                           class="form-first-name form-control" id="form-first-name">
                                </div>
                                <div class="form-group">
                                    <label class="sr-only" for="form-last-name">Last name</label>
                                    <input type="password" name="password" placeholder="password"
                                           class="form-last-name form-control" id="form-last-name">
                                </div>
                                <%--<div class="form-group">--%>
                                <%--<label class="sr-only" for="form-email">Email</label>--%>
                                <%--<input type="text" name="form-email" placeholder="Email..."--%>
                                <%--class="form-email form-control" id="form-email">--%>
                                <%--</div>--%>
                                <%--<div class="form-group">--%>
                                <%--<label class="sr-only" for="form-about-yourself">About yourself</label>--%>
                                <%--<textarea name="form-about-yourself" placeholder="About yourself..."--%>
                                <%--class="form-about-yourself form-control"--%>
                                <%--id="form-about-yourself"></textarea>--%>
                                <%--</div>--%>
                                <button type="submit" class="btn">Sign me up!</button>
                            </form>
                        </div>
                    </div>

                </div>
            </div>

        </div>
    </div>

</div>

<!-- Footer -->
<footer>
    <div class="container">
        <c:set var="error" value="${error}"/>
        <c:if test="${not empty error or not empty param.containsKey('error')}">
            <div class="alert alert-danger alert-dismissable fade in" id="error_alert">
                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                <strong>${error}!</strong> Change input and try again.
            </div>
            <script>
                setTimeout(function () {
                    $(".alert").alert('close');
                }, 2000);
            </script>
        </c:if>
    </div>
</footer>


</body>
</html>
