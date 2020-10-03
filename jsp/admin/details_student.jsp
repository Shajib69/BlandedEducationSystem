<%@page import="java.util.Date"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@include file="header.jsp" %>
<div id="page-wrapper" style="min-height: 800px;">
    <div class="container-fluid">
        <!-- Page Heading -->
        <div class="row">
            <div class="col-lg-12">
                <h2 class="page-header">
                    <i class="fa fa-user-plus"></i> Student Detiils
                </h2>
            </div>
        </div>

        <div class="row">
            <div class="col-md-12">
                <c:if test="${sm != null}">
                    <div class="alert alert-success alert-dismissable">
                        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                        <strong>Success!</strong> ${sm}
                    </div>
                </c:if>
                <c:if test="${em != null}">
                    <div class="alert alert-danger alert-dismissable">
                        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                        <strong>Error!</strong> ${em}
                    </div>
                </c:if>
            </div>
        </div>

        <div class="row">

            <img width="200px" class="img-thumbnail pull-right" src="${pageContext.request.contextPath}/resources/${student.photo}"/>
            <div class="col-md-6">
                <table class="table table-bordered table-striped table-responsive">
                    <tr>
                        <th colspan="3" style="text-align: center;">
                            Student Information
                        </th>
                    </tr>
                    <tr>
                        <td>Student Name</td>
                        <td>:</td>
                        <td>${student.studentName}</td>
                    </tr>
                    <tr>
                        <td>Father Name</td>
                        <td>:</td>
                        <td>${student.fatherName}</td>
                    </tr>
                    <tr>
                        <td>Mother Name</td>
                        <td>:</td>
                        <td>${student.motherName}</td>
                    </tr>
                    <tr>
                        <td>Batch</td>
                        <td>:</td>
                        <td>${student.batch.batchName}</td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>:</td>
                        <td></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>:</td>
                        <td></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>:</td>
                        <td></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>:</td>
                        <td></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>:</td>
                        <td></td>
                    </tr>
                </table>

                <a href="${pageContext.request.contextPath}/admin/list_student" class="btn btn-success pull-right">Back to list</a>
            </div>



        </div>





    </div>
</div>

<%@include file="footer.jsp" %>