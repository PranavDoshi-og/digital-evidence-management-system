<%@ include file="includes/header.jsp" %>

<div class="container">
    <div class="row justify-content-center mt-5">
        <div class="col-md-4">
            <div class="card shadow-lg">
                <div class="card-body p-5 text-center">
                    <div class="mb-4">
                        <h2 class="fw-bold">TERMINAL_LOGIN</h2>
                        <small class="text-secondary text-uppercase">Authorized Access Only</small>
                    </div>
                    
                    <form action="login" method="POST">
                        <div class="mb-3 text-start">
                            <label class="form-label small text-secondary">IDENTIFIER</label>
                            <input type="text" name="username" class="form-control bg-dark text-white border-secondary" placeholder="Username" required>
                        </div>
                        <div class="mb-4 text-start">
                            <label class="form-label small text-secondary">ACCESS_KEY</label>
                            <input type="password" name="password" class="form-control bg-dark text-white border-secondary" placeholder="Password" required>
                        </div>
                        <button type="submit" class="btn btn-primary w-100">INITIALIZE_SESSION</button>
                    </form>
                    
                    <% if(request.getParameter("error") != null) { %>
                        <div class="mt-3 text-danger small">
                            [!] INVALID_CREDENTIALS_REJECTED
                        </div>
                    <% } %>
                </div>
            </div>
            <p class="text-center mt-4 small text-secondary">
                Secure Hash Protocol: SHA-256 Enabled
            </p>
        </div>
    </div>
</div>
</body>
</html>