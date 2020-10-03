<%@page import="java.util.Date"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@include file="header.jsp" %>
<div id="page-wrapper" style="min-height: 800px;">
    <div class="container-fluid">
        <!-- Page Heading -->
        <div class="row">
            <div class="col-lg-12">
                <h2 class="page-header">
                    <i class="fa fa-user-plus"></i> Add Student
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


            <div class="col-md-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4>Register Student</h4>
                    </div>
                    <div class="panel-body">
                        <form action="${pageContext.request.contextPath}/admin/saveStudent" method="post" commandName="users">
                            <div class="row">
                                <div class="form-group col-md-4">
                                    <label class="control-label">Student Name:</label>
                                    <input name="studentName" type="text" class="form-control"/>
                                </div>
                                <div class="form-group col-md-4">
                                    <label class="control-label">Email:</label>
                                    <input name="email" type="eamil" class="form-control"/>
                                </div>
                                <div class="form-group col-md-4">
                                    <label class="control-label">Bathc Name:</label>
                                    <select name="batchId" class="form-control">
                                        <option value="">-- select batch --</option>
                                        <option value="">JAVA</option>
                                    </select>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-md-6">
                                    <label class="control-label">Father Name:</label>
                                    <input name="fatherName" type="text" class="form-control"/>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label">Mother Name:</label>
                                    <input name="motherName" type="text" class="form-control"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-md-6">
                                    <label class="control-label">Gender:</label>
                                    <div class="input-group">
                                        <input type="radio" name="gender" value="male"/> Male
                                        <input type="radio" name="gender" value="female"/> Female
                                    </div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label">Date of Birth:</label>
                                    <input name="dob" type="date" class="form-control"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-md-6">
                                    <label class="control-label">Present Address:</label>
                                    <textarea name="presentAddress" class="form-control"></textarea>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label">Permanent Address:</label>
                                    <textarea name="permanentAddress" class="form-control"></textarea>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-md-6">
                                    <label class="control-label">Religion:</label>
                                    <input name="religion" type="text" class="form-control"/>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label">Nationality:</label>
                                    <input name="nationality" type="text" class="form-control"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-md-3">
                                    <label class="control-label">Father Mobile:</label>
                                    <input name="fatherMobile" type="text" class="form-control"/>
                                </div>
                                <div class="form-group col-md-3">
                                    <label class="control-label">Mother Mobile:</label>
                                    <input name="motherMobile" type="text" class="form-control"/>
                                </div>
                                <div class="form-group col-md-3">
                                    <label class="control-label">Home Mobile:</label>
                                    <input name="homeMobile" type="text" class="form-control"/>
                                </div>
                                <div class="form-group col-md-3">
                                    <label class="control-label">Student Mobile:</label>
                                    <input name="studentMobile" type="text" class="form-control"/>
                                </div>
                            </div>



                            <div class="row">
                                <div class="form-group col-md-6">
                                    <label class="control-label">Register Date:</label>
                                    <input name="registerDate" type="date" class="form-control"/>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="control-label">Upload Photo:</label>
                                    <div class="input-group">
                                        <label class="input-group-btn">
                                            <span class="btn btn-primary">
                                                Brows &hellip; <input accept=".jpg, .jpeg, .png" type="file" name="photo" style="display: none;" multiple>
                                            </span>
                                        </label>
                                        <input type="text" class="form-control" readonly>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label">Status:</label>
                                <input type="checkbox" name="status"/> Active
                            </div>




                            <div class="form-group"> 
                                <button type="submit" class="btn btn-success">Create</button>
                                <button type="reset" class="btn btn-default">Reset</button>
                            </div>
                        </form>
                    </div>
                    <div class="panel-footer"></div>
                </div>
            </div>



        </div>





    </div>
</div>

<%@include file="footer.jsp" %>