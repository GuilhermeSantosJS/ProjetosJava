<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<meta charset="ISO-8859-1">
<title>Datas</title>
</head>
<body>
    
    <form action="calcularDataFinal" method="post">
    
    <label>Data Inicial</label>
    <input id="datepicker" name="datepicker">
    
    <label>Tempo em Horas</label>
    <input type="text" id="tempo" name="tempo">
    
    <input type="submit" value="Calcular">
    
    </form>
   <br/>
   <br/>

<label>Data Final</label>
<input type="text" id="dataFinal" name="dataFinal" readonly="readonly"
value="${dataFinal}">

 <label>Diasl</label>
<input type="text" id="dias" name="dias" readonly="readonly"
value="${dias}">


</body>
<script type="text/javascript">
$(function(){
  $('#datepicker').datepicker({dateFormat: 'dd/mm/yyyy'});	
});


</script>

</html>