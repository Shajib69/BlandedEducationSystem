<%@include file="header.jsp" %>

<div id="page-wrapper"  style="min-height: 800px;">

    <div class="container-fluid">
        
        <div class="row">
            <div class="col-md-12">
                ${sm}
                ${em}
            </div>
        </div>
        
        <!-- Page Heading -->
        <div class="row">
            <div class="col-lg-12">
                <h2 class="page-header">
                    <i class="fa fa-database"></i> BackUp Database SQL File
                </h2>
            </div>
        </div>
        
        
        <div class="row">
            <div class="col-md-12">
                <a href="${pageContext.request.contextPath}/admin/doBackUp" class="btn btn-lg btn-primary"><i class="fa fa-download"></i> Back Up</a>
                <a href="${pageContext.request.contextPath}/admin/doReStore" class="btn btn-lg btn-warning"><i class="fa fa-upload"></i> Restore</a>
                <a href="" class="btn btn-success btn-lg pull-right"><i class="fa fa-mail-forward"></i> Email BackUp</a>
            </div>
        </div>
        
        
    </div>
    
</div>

<%@include file="footer.jsp" %>