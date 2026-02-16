<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DEMS | Digital Evidence Vault</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=JetBrains+Mono&family=Roboto:wght@300;400;700&display=swap" rel="stylesheet">
    <style>
        body { 
            background-color: #0a192f; /* Midnight Navy */
            color: #ccd6f6; /* Steel Gray */
            font-family: 'Roboto', sans-serif;
        }
        .navbar { background-color: #112240; border-bottom: 2px solid #64ffda; }
        .card { background-color: #112240; border: 1px solid #233554; color: #ccd6f6; }
        .hash-font { font-family: 'JetBrains Mono', monospace; color: #64ffda; font-size: 0.85rem; }
        .btn-primary { background-color: #64ffda; border: none; color: #0a192f; font-weight: bold; }
        .btn-primary:hover { background-color: #52e0c4; color: #0a192f; }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark mb-4">
    <div class="container">
        <a class="navbar-brand fw-bold" href="#">
            <span style="color: #64ffda;">&gt;</span> DEMS_CORE
        </a>
        <div class="navbar-nav ms-auto">
            <% if(session.getAttribute("user") != null) { %>
                <span class="nav-link text-info">Investigator: ${user.username}</span>
                <a class="nav-link btn btn-outline-danger btn-sm ms-3" href="logout">LOGOUT</a>
            <% } %>
        </div>
    </div>
</nav>