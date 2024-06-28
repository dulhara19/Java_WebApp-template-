<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Super Admin Dashboard</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container mt-5">
        <h2>Super Admin Dashboard</h2>
        <div class="table-responsive">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody id="admin-requests">
                    <!-- Sample data, replace with dynamic data from the database -->
                    <tr>
                        <td>John Doe</td>
                        <td>john.doe@example.com</td>
                        <td>123-456-7890</td>
                        <td>
                            <button class="btn btn-success" onclick="acceptAdmin(this)">Accept</button>
                            <button class="btn btn-danger" onclick="deleteAdmin(this)">Delete</button>
                        </td>
                    </tr>
                    <!-- More rows as needed -->
                </tbody>
            </table>
        </div>
    </div>

    <script>
        function acceptAdmin(button) {
            // Get the row containing the button
            var row = button.parentNode.parentNode;
            // Extract admin details from the row
            var name = row.cells[0].innerText;
            var email = row.cells[1].innerText;
            var phone = row.cells[2].innerText;

            // Example AJAX call to backend servlet to accept admin request
            $.ajax({
                url: 'acceptAdminServlet',
                type: 'POST',
                data: {
                    name: name,
                    email: email,
                    phone: phone
                },
                success: function(response) {
                    // On success, remove the row from the table
                    row.parentNode.removeChild(row);
                },
                error: function(error) {
                    alert('Error accepting admin request.');
                }
            });
        }

        function deleteAdmin(button) {
            // Get the row containing the button
            var row = button.parentNode.parentNode;

            // Example AJAX call to backend servlet to delete admin request
            $.ajax({
                url: 'deleteAdminServlet',
                type: 'POST',
                data: {
                    email: row.cells[1].innerText // assuming email is unique
                },
                success: function(response) {
                    // On success, remove the row from the table
                    row.parentNode.removeChild(row);
                },
                error: function(error) {
                    alert('Error deleting admin request.');
                }
            });
        }
    </script>
</body>
</html>
