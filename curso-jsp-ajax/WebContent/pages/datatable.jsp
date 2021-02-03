<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DataTable JQuery</title>


<link rel="stylesheet" href="//cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script type="text/javascript" src="//cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>

</head>
<body>
<table id="usuario" class="display" style="width:100%">
        <thead>
            <tr>
                <th>Id</th>
                <th>Login</th>
            </tr>
        </thead>
</table>


</body>
<script type="text/javascript">

$(document).ready(function() {
    $('#usuario').DataTable( {
        "processing": true,
        "serverSide": true,
        "ajax": "carregarDadosDataTable"
    } );
} );

</script>
</html>