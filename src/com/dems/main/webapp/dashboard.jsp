<%@ page import="com.dems.model.User, com.dems.model.Evidence, com.dems.dao.EvidenceDAO, java.util.List" %>
<%@ include file="includes/header.jsp" %>

<%
    // Security Check: Redirect to login if session is empty
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("index.jsp");
        return;
    }

    // Fetch evidence using your existing DAO logic
    EvidenceDAO evidenceDAO = new EvidenceDAO();
    List<Evidence> evidenceList = evidenceDAO.getAllEvidence();
%>

<div class="container-fluid px-4">
    <div class="row">
        <div class="col-12 d-flex justify-content-between align-items-center mb-4 border-bottom border-secondary pb-3">
            <div>
                <h1 class="h3 fw-bold mb-0">EVIDENCE_VAULT</h1>
                <p class="small text-secondary mb-0">Status: <span class="text-success">SYSTEM_ONLINE</span> | Encryption: SHA-256</p>
            </div>
            <a href="add-evidence.jsp" class="btn btn-primary btn-sm">+ NEW_ENTRY</a>
        </div>

        <div class="col-12">
            <div class="card shadow">
                <div class="card-body p-0">
                    <table class="table table-dark table-hover mb-0">
                        <thead class="table-secondary">
                            <tr class="small text-uppercase">
                                <th class="ps-4">ID</th>
                                <th>Evidence_Name</th>
                                <th>Integrity_Hash (SHA-256)</th>
                                <th>Status</th>
                                <th class="text-end pe-4">Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% if(evidenceList.isEmpty()) { %>
                                <tr>
                                    <td colspan="5" class="text-center py-5 text-secondary">NO_RECORDS_FOUND_IN_VAULT</td>
                                </tr>
                            <% } else { 
                                for(Evidence e : evidenceList) { %>
                                <tr>
                                    <td class="ps-4 text-secondary">#<%= e.getId() %></td>
                                    <td class="fw-bold"><%= e.getFileName() %></td>
                                    <td>
                                        <code class="hash-font"><%= e.getFileHash().substring(0, 32) %>...</code>
                                    </td>
                                    <td>
                                        <span class="badge bg-success-subtle text-success border border-success px-2 py-1">VERIFIED</span>
                                    </td>
                                    <td class="text-end pe-4">
                                        <button class="btn btn-sm btn-outline-info">VIEW_DETAILS</button>
                                    </td>
                                </tr>
                            <% } } %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<footer class="mt-5 text-center text-secondary small pb-4">
    &copy; 2026 DIGITAL_EVIDENCE_MANAGEMENT_SYSTEM | INTERNAL_USE_ONLY
</footer>
</body>
</html>