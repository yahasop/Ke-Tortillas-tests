<%@ page import="com.example.webapp.modelos.Carro" %>
<%@ page import="com.example.webapp.modelos.ItemCarro" %>
<%@page contentType="text/html; ISO-8859-1" pageEncoding="utf-8" %>

<%
    String username = (String) session.getAttribute("username");
    Carro carro = (Carro) session.getAttribute("carro");
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="apple-touch-icon" sizes="180x180" href="IMG\Trigo-removebg-preview.png">
    <link rel="icon" type="image/png" sizes="32x32" href="IMG\Trigo-removebg-preview.png">
    <link rel="icon" type="image/png" sizes="16x16" href="IMG\Trigo-removebg-preview.png">
    <link rel="manifest" href="IMG\Trigo-removebg-preview.png">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
          integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" type="text/css" href="maquetado_Inicio.css">
    <link rel="stylesheet" type="text/css" href="maquetado_Carrito.css">
    <title>Ke Tortillas</title>
</head>

<body>
<header class="header">
    <div class="menu container">

        <a href="#" class="logo">Ke Tortillas</a><img src="IMG\Trigo-removebg-preview.png" alt="Logo" class="logo"
                                                      width="50" height="60" class="d-inline-block align-text">
        <input type="checkbox" id="menu" />
        <label for="menu">
            <img src="IMG/Menu.png" class="menu-icono" alt="">
        </label>
        <nav class="navbar">
            <ul>
                <li><a href="index.jsp">INICIO</a></li>
                <li><a href="Pagina_Productos.jsp">PRODUCTOS</a></li>
                <li><a href="Pagina_CarritoCompras.jsp">CARRITO</a></li>
                <li><a href="#">CONTACTO</a></li>
                <%if(username == null || username.isEmpty()){%>
                <li><a href="registro.jsp">REGISTRO</a></li>
                <%} else {%>
                <li><a href="#">Hola, <%=username%>:)</a></li>
                <li><a href="${pageContext.request.contextPath}/logout">Cerrar sesion</a></li>
                <%}%>
            </ul>
        </nav>
    </div>
    <div class="header-content container">

        <h1>Ke Tortillas</h1>

        <p>
            Las tortillas de harina estilo Sonorense son una opción deliciosa y nutritiva para incluir en tu dieta
            diaria. Originarias del estado de Sonora en México, estas tortillas son muy diferentes a las
            tradicionales tortillas de maíz que se consumen en otras partes del país.
            Las tortillas de harina estilo Sonorense son más suaves y elásticas que las de maíz, lo que las hace
            ideales para enrollar y hacer burritos, quesadillas y otros platillos.
        </p>
        <br><br><a href="#" class="btn-1">Información</a>
    </div>
</header>

<section class="contenedor">
    <div class="contenedor-items">
        <div class="item">
            <span class="titulo-item">Tortilla Tradicional</span><br>
            <img src="IMG/Tortilla_Tradicional.jpeg" alt="" class="img-item" width="350px" height="350px">
            <span class="precio-item">$35.00</span>
            <p>Paquete de 10 tortillas de harina.</p>
            <a href="${pageContext.request.contextPath}/agregar-carro?id=1"><button class="boton-item">Agregar al carrito</button></a>
        </div>
        <div class="item">
            <span class="titulo-item">Tortilla para taco</span><br>
            <img src="IMG/Tortilla_Taco.jpeg" alt="" class="img-item" width="350px" height="350px">
            <span class="precio-item">$40.00</span>
            <p>Paquete de 10 tortillas para taco</p>
            <button class="boton-item">Agregar al carrito</button>
        </div>
        <div class="item">
            <span class="titulo-item">Tortilla sobaquera</span><br>
            <img src="IMG/Tortilla_Sobaquera.jpeg" alt="" class="img-item" width="350px" height="350px">
            <span class="precio-item">$120.00</span>
            <p>Paquete de 5 tortillas para burro</p>
            <button class="boton-item">Agregar al carrito</button>
        </div>
    </div>
    <%if(carro == null || carro.getItems().isEmpty());
    else if((carro.findProducto("1").isPresent())){
    if(carro.findProducto("1").get().getCantidad() > 0){%>
    <div class="carrito">
        <div class="header-carrito">
            <h2>Tu carrito</h2>
        </div>

        <div class="carrito-items">
            <div class="carrito-item">
                <img src="IMG/Tortilla_Tradicional.jpeg" alt="" width="80px">
                <div class="cassito-item-detalles">
                    <span class="carrito-item-titulo">Tortilla tradicional</span>
                    <div class="selector-cantidad">
                        <a href="${pageContext.request.contextPath}/eliminar-carro?id=1"><i class="fa-solid fa-minus restar-cantidad"></i></a>
                        <input type="text" value="<%out.print(carro.findProducto("1").get().getCantidad());%>" class="carrito-item-cantidad" disabled>
                        <a href="${pageContext.request.contextPath}/agregar-carro?id=1"><i class="fa-solid fa-plus sumar-cantidad"></i></a>
                    </div>
                    <span class="carrito-item-precio"><%out.print("$" + (carro.findProducto("1").get().getCantidad() * 35) + ".00");%></span>
                </div>
                <span class="btn-eliminar">
                        <a href="${pageContext.request.contextPath}/eliminar-carro?id=1&all=1"><i class="fa-solid fa-trash-can"></i></a>
                    </span>
            </div>
            <%}%>
            <%} else if((carro.findProducto("2").isPresent()) || (carro.findProducto("2").get().getCantidad() > 0)){%>
            <div class="carrito-item">
                <img src="IMG/Tortilla_Taco.jpeg" alt="" width="80px">
                <div class="cassito-item-detalles">
                    <span class="carrito-item-titulo">Tortilla Taco</span>
                    <div class="selector-cantidad">
                        <i class="fa-solid fa-minus restar-cantidad"></i>
                        <input type="text" value="2" class="carrito-item-cantidad" disabled>
                        <i class="fa-solid fa-plus sumar-cantidad"></i>
                    </div>
                    <span class="carrito-item-precio">$40.00</span>
                </div>
                <span class="btn-eliminar">
                        <i class="fa-solid fa-trash-can"></i>
                    </span>
            </div>
            <%}%>
        </div>

        <%if(carro != null){
        if(!carro.getItems().isEmpty()){%>
        <div class="carrito-total">
            <div class="fila">
                <strong>El total de tu compra es: </strong>
                <span class="carrito-precio-total">
                        <%out.print("$" + (carro.getTotal()) + ".00");%>
                    </span>
            </div>
            <a href="formulario_Pago.jsp"><button class="btn-pagar">Pagar<i class="fa-solid fa-cart-shopping"></i></button></a>

        </div>
        <%}
        }%>
    </div>
</section>
</body>

</html>