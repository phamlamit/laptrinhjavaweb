<%@include file="/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:url var="customerAPI" value="/api/customer"/>
<c:url var="saveTransactionAPI" value="/api/customer/transaction"/>
<html>
<head>
    <title>Sua Khach Hang</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try {
                    ace.settings.check('breadcrumbs', 'fixed')
                } catch (e) {
                }
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Home</a>
                </li>
                <li class="active">Edit</li>
            </ul><!-- /.breadcrumb -->


        </div>
        <div class="page-content">
            <div class="row">
                <div class="widget-box">
                    <br>
                    <br>
                    <!-- PAGE CONTENT BEGINS -->
                    <form:form role="form" id="formEdit" action="" method="POST" commandName="model"
                               cssClass="form-horizontal">
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right">
                                        Tên Đầy Đủ </label>

                                    <div class="col-sm-9">
                                        <form:input path="fullname" cssClass="col-sm-9" id="fullname"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" for="phone">
                                        Số điện thoại </label>
                                    <div class="col-sm-9">
                                        <form:input path="phone" cssClass="col-sm-9"/>
                                    </div>

                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" for="email">
                                        Email </label>
                                    <div class="col-sm-9">
                                        <form:input path="email" cssClass="col-sm-9"/>
                                    </div>

                                </div>
                                <div class="form-group">
                                    <div class="col-sm-9">
                                        <input type="number" id="customerId" hidden value="${model.id}"/>
                                    </div>

                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" for="requirement">
                                        Nhu Cầu </label>
                                    <div class="col-sm-9">
                                        <form:input path="requirement" cssClass="col-sm-9"/>
                                    </div>

                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" for="note">
                                        Ghi Chú </label>
                                    <div class="col-sm-9">
                                        <form:input path="note" cssClass="col-sm-9"/>
                                    </div>

                                </div>


                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right">
                                    </label>

                                    <div class="col-md-9">
                                        <button value="${model.id}" class="btn btn-info" id="btnAddBuilding"
                                                type="button">
                                            <i class="ace-icon fa fa-check bigger-110"></i>
                                            <c:choose>
                                                <c:when test="${model.id !=null}">
                                                    Cập Nhập
                                                </c:when>
                                                <c:otherwise>
                                                    Thêm Khách Hàng
                                                </c:otherwise>
                                            </c:choose>
                                        </button>&nbsp; &nbsp; &nbsp;
                                        <button class="btn" type="reset">
                                            <i class="ace-icon fa fa-undo bigger-110"></i>
                                            Hủy
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form:form>
                </div>
                <br>
                <br>
                <div class="row mt-4">
                    <div class="col-xs-12">
                        <div>
                            <c:forEach var="item" items="${mapTransactionTypes}">
                                <table style="width:100%" id="dynamic-table" class="table table-striped table-bordered table-hover">
                                    <caption id="${item.key}" value="${item.key}">
                                        <h3 style="color: blue">${item.value}
                                            <button class="btn btn-white btn-info btn-bold" data-toggle="tooltip"
                                                    data-value="${item.key}" onclick="addTransaction(this)"
                                                    title="Thêm Giao Dịch">
                                                <i class="fa fa-plus-circle	"></i>
                                            </button>
                                        </h3>
                                    </caption>
                                    <thead>
                                    <tr>
                                        <th style="width:30%">Ngày Tạo</th>
                                        <th style="width:70%">Ghi Chú</th>

                                    </tr>
                                    </thead>
                                    <tbody id="tbody">
                                    <c:forEach var="mapTransaction" items="${mapTransaction}">
                                        <tr>
                                            <c:choose>
                                                <c:when test="${mapTransaction.key == item.key}">
                                                    <c:forEach var="transaction" items="${mapTransaction.value}">

                                                        <td>${transaction.createdDate}</td>
                                                        <td>${transaction.note}</td>

                                                    </c:forEach>
                                                </c:when>
                                                <c:otherwise>
                                                </c:otherwise>
                                            </c:choose>

                                        </tr>
                                    </c:forEach>
                                    <tr>
                                        <td></td>
                                        <td><input style="width:70%" type="text" id="noteTransaction"></td>
                                    </tr>
                                    </tbody>
                                </table>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.col -->

    </div>
</div>
<!--[if !IE]> -->
<script src="assets/js/jquery.2.1.1.min.js"></script>

<!-- <![endif]-->

<!--[if IE]>
<script src="assets/js/jquery.1.11.1.min.js"></script>
<![endif]-->

<!--[if !IE]> -->
<script type="text/javascript">
    window.jQuery || document.write("<script src='assets/js/jquery.min.js'>" + "<" + "/script>");
</script>

<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
    window.jQuery || document.write("<script src='assets/js/jquery1x.min.js'>" + "<" + "/script>");
</script>
<![endif]-->
<script type="text/javascript">
    if ('ontouchstart' in document.documentElement) document.write("<script src='assets/js/jquery.mobile.custom.min.js'>" + "<" + "/script>");
</script>
<script src="assets/js/bootstrap.min.js"></script>

<!-- page specific plugin scripts -->

<!--[if lte IE 8]>
<script src="assets/js/excanvas.min.js"></script>
<![endif]-->
<script src="assets/js/jquery-ui.custom.min.js"></script>
<script src="assets/js/jquery.ui.touch-punch.min.js"></script>
<script src="assets/js/jquery.easypiechart.min.js"></script>
<script src="assets/js/jquery.sparkline.min.js"></script>
<script src="assets/js/jquery.flot.min.js"></script>
<script src="assets/js/jquery.flot.pie.min.js"></script>
<script src="assets/js/jquery.flot.resize.min.js"></script>

<!-- ace scripts -->
<script src="assets/js/ace-elements.min.js"></script>
<script src="assets/js/ace.min.js"></script>

<!-- inline scripts related to this page -->
<script type="text/javascript">
    jQuery(function ($) {
        $('.easy-pie-chart.percentage').each(function () {
            var $box = $(this).closest('.infobox');
            var barColor = $(this).data('color') || (!$box.hasClass('infobox-dark') ? $box.css('color') : 'rgba(255,255,255,0.95)');
            var trackColor = barColor == 'rgba(255,255,255,0.95)' ? 'rgba(255,255,255,0.25)' : '#E2E2E2';
            var size = parseInt($(this).data('size')) || 50;
            $(this).easyPieChart({
                barColor: barColor,
                trackColor: trackColor,
                scaleColor: false,
                lineCap: 'butt',
                lineWidth: parseInt(size / 10),
                animate: /msie\s*(8|7|6)/.test(navigator.userAgent.toLowerCase()) ? false : 1000,
                size: size
            });
        })

        $('.sparkline').each(function () {
            var $box = $(this).closest('.infobox');
            var barColor = !$box.hasClass('infobox-dark') ? $box.css('color') : '#FFF';
            $(this).sparkline('html',
                {
                    tagValuesAttribute: 'data-values',
                    type: 'bar',
                    barColor: barColor,
                    chartRangeMin: $(this).data('min') || 0
                });
        });


        //flot chart resize plugin, somehow manipulates default browser resize event to optimize it!
        //but sometimes it brings up errors with normal resize event handlers
        $.resize.throttleWindow = false;

        var placeholder = $('#piechart-placeholder').css({'width': '90%', 'min-height': '150px'});
        var data = [
            {label: "social networks", data: 38.7, color: "#68BC31"},
            {label: "search engines", data: 24.5, color: "#2091CF"},
            {label: "ad campaigns", data: 8.2, color: "#AF4E96"},
            {label: "direct traffic", data: 18.6, color: "#DA5430"},
            {label: "other", data: 10, color: "#FEE074"}
        ]

        function drawPieChart(placeholder, data, position) {
            $.plot(placeholder, data, {
                series: {
                    pie: {
                        show: true,
                        tilt: 0.8,
                        highlight: {
                            opacity: 0.25
                        },
                        stroke: {
                            color: '#fff',
                            width: 2
                        },
                        startAngle: 2
                    }
                },
                legend: {
                    show: true,
                    position: position || "ne",
                    labelBoxBorderColor: null,
                    margin: [-30, 15]
                }
                ,
                grid: {
                    hoverable: true,
                    clickable: true
                }
            })
        }

        drawPieChart(placeholder, data);

        /**
         we saved the drawing function and the data to redraw with different position later when switching to RTL mode dynamically
         so that's not needed actually.
         */
        placeholder.data('chart', data);
        placeholder.data('draw', drawPieChart);


        //pie chart tooltip example
        var $tooltip = $("<div class='tooltip top in'><div class='tooltip-inner'></div></div>").hide().appendTo('body');
        var previousPoint = null;

        placeholder.on('plothover', function (event, pos, item) {
            if (item) {
                if (previousPoint != item.seriesIndex) {
                    previousPoint = item.seriesIndex;
                    var tip = item.series['label'] + " : " + item.series['percent'] + '%';
                    $tooltip.show().children(0).text(tip);
                }
                $tooltip.css({top: pos.pageY + 10, left: pos.pageX + 10});
            } else {
                $tooltip.hide();
                previousPoint = null;
            }

        });

        /////////////////////////////////////
        $(document).one('ajaxloadstart.page', function (e) {
            $tooltip.remove();
        });


        var d1 = [];
        for (var i = 0; i < Math.PI * 2; i += 0.5) {
            d1.push([i, Math.sin(i)]);
        }

        var d2 = [];
        for (var i = 0; i < Math.PI * 2; i += 0.5) {
            d2.push([i, Math.cos(i)]);
        }

        var d3 = [];
        for (var i = 0; i < Math.PI * 2; i += 0.2) {
            d3.push([i, Math.tan(i)]);
        }


        var sales_charts = $('#sales-charts').css({'width': '100%', 'height': '220px'});
        $.plot("#sales-charts", [
            {label: "Domains", data: d1},
            {label: "Hosting", data: d2},
            {label: "Services", data: d3}
        ], {
            hoverable: true,
            shadowSize: 0,
            series: {
                lines: {show: true},
                points: {show: true}
            },
            xaxis: {
                tickLength: 0
            },
            yaxis: {
                ticks: 10,
                min: -2,
                max: 2,
                tickDecimals: 3
            },
            grid: {
                backgroundColor: {colors: ["#fff", "#fff"]},
                borderWidth: 1,
                borderColor: '#555'
            }
        });


        $('#recent-box [data-rel="tooltip"]').tooltip({placement: tooltip_placement});

        function tooltip_placement(context, source) {
            var $source = $(source);
            var $parent = $source.closest('.tab-content')
            var off1 = $parent.offset();
            var w1 = $parent.width();

            var off2 = $source.offset();
            //var w2 = $source.width();

            if (parseInt(off2.left) < parseInt(off1.left) + parseInt(w1 / 2)) return 'right';
            return 'left';
        }


        $('.dialogs,.comments').ace_scroll({
            size: 300
        });


        //Android's default browser somehow is confused when tapping on label which will lead to dragging the task
        //so disable dragging when clicking on label
        var agent = navigator.userAgent.toLowerCase();
        if ("ontouchstart" in document && /applewebkit/.test(agent) && /android/.test(agent))
            $('#tasks').on('touchstart', function (e) {
                var li = $(e.target).closest('#tasks li');
                if (li.length == 0) return;
                var label = li.find('label.inline').get(0);
                if (label == e.target || $.contains(label, e.target)) e.stopImmediatePropagation();
            });

        $('#tasks').sortable({
                opacity: 0.8,
                revert: true,
                forceHelperSize: true,
                placeholder: 'draggable-placeholder',
                forcePlaceholderSize: true,
                tolerance: 'pointer',
                stop: function (event, ui) {
                    //just for Chrome!!!! so that dropdowns on items don't appear below other items after being moved
                    $(ui.item).css('z-index', 'auto');
                }
            }
        );
        $('#tasks').disableSelection();
        $('#tasks input:checkbox').removeAttr('checked').on('click', function () {
            if (this.checked) $(this).closest('li').addClass('selected');
            else $(this).closest('li').removeClass('selected');
        });


        //show the dropdowns on top or bottom depending on window height and menu position
        $('#task-tab .dropdown-hover').on('mouseenter', function (e) {
            var offset = $(this).offset();

            var $w = $(window)
            if (offset.top > $w.scrollTop() + $w.innerHeight() - 100)
                $(this).addClass('dropup');
            else $(this).removeClass('dropup');
        });

    })

    $('#btnAddBuilding').click(function (e) {
        e.preventDefault();
        var data = {};
        var formData = $('#formEdit').serializeArray();
        $.each(formData, function (index, v) {
            data['' + v.name + ''] = v.value;
        });
        data['id'] = $('#btnAddBuilding').val();
        $.ajax({
            type: 'POST',
            url: '${customerAPI}',
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json",
            success: function (response) {
                console.log("sucess" + response);
            },
            error: function (response) {
                console.log("failed" + response);
            },
        });
    });

    function addTransaction(e) {
        var id = $('#customerId').val();
        var code = $(e).closest('caption').attr('value');
        var note = $('#noteTransaction').val();
        var transactionDTO = {
            customerId: id,
            code: code,
            note : note,
        }
        $.ajax({
            type: 'POST',
            url: '${saveTransactionAPI}',
            data: JSON.stringify(transactionDTO),
            dataType: "json",
            contentType: "application/json",
            success: function (response) {
                console.log("sucess" + response);
            },
            error: function (response) {
                console.log("failed" + response);
            },
        });
    }

</script>

</body>
</html>