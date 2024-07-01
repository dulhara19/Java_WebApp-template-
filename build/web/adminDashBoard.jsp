<%@ page import="java.util.List" %>
<%@ page import="dao.AdminRequest" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Requests</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <script>
        function submitForm(action, aid, name, phone, email) {
            var form = document.createElement("form");
            form.method = "post";
            form.action = "adminRequests";
            
            var actionInput = document.createElement("input");
            actionInput.type = "hidden";
            actionInput.name = "action";
            actionInput.value = action;
            form.appendChild(actionInput);
            
            var aidInput = document.createElement("input");
            aidInput.type = "hidden";
            aidInput.name = "aid";
            aidInput.value = aid;
            form.appendChild(aidInput);

            var nameInput = document.createElement("input");
            nameInput.type = "";
            nameInput.name = "name";
            nameInput.value = name;
            form.appendChild(nameInput);

            var phoneInput = document.createElement("input");
            phoneInput.type = "hidden";
            phoneInput.name = "phone";
            phoneInput.value = phone;
            form.appendChild(phoneInput);

            var emailInput = document.createElement("input");
            emailInput.type = "hidden";
            emailInput.name = "email";
            emailInput.value = email;
            form.appendChild(emailInput);

            document.body.appendChild(form);
            form.submit();
        }
    </script>
</head>
<body>
    <div class="container mt-4">
        <h2>Admin Requests</h2>  
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>AID</th>
                    <th>Name</th>
                    <th>Phone</th>
                    <th>Email</th>
                    <th>Actions</th>
                    </tr>
            </thead>
            <tbody>
                <%
                    List<AdminRequest> adminRequestList = (List<AdminRequest>) request.getAttribute("adminRequestList");
                    if (adminRequestList != null) {
                        for (AdminRequest adminRequest : adminRequestList) {
                %>
                <tr>
                    <td><%= adminRequest.getAid() %></td>
                    <td><%= adminRequest.getName() %></td>
                    <td><%= adminRequest.getPhone() %></td>
                    <td><%= adminRequest.getEmail() %></td>
                    <td>
                        <button class="btn btn-success" onclick="submitForm('accept', '<%= adminRequest.getAid() %>', '<%= adminRequest.getName() %>', '<%= adminRequest.getPhone() %>', '<%= adminRequest.getEmail() %>')">Accept</button>
                        <button class="btn btn-danger" onclick="submitForm('delete', '<%= adminRequest.getAid() %>', '', '', '')">Delete</button>
                    </td>
                </tr>
                <%
                        }
                    }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>
