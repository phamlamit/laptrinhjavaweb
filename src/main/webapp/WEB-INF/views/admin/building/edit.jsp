<%@include file="/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:url var="buildingAPI" value="/api/building"/>
<html>
<head>
    <title>Sua Toa Nha</title>
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
                <li class="active">Dashboard</li>
            </ul><!-- /.breadcrumb -->


        </div>
        <div class="page-content">

            <div class="row">
                <div class="col-xs-12">
                    <div class="row">
                        <div class="col-xs-12">
                            <!-- PAGE CONTENT BEGINS -->
                            <form:form role="form" id="formEdit" action="" method="POST" commandName="model"
                                       cssClass="form-horizontal">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right">
                                        Tên Tòa Nhà </label>

                                    <div class="col-sm-9">
                                        <form:input path="name" cssClass="col-sm-5"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" for="numberOfBasement">
                                        Số Tầng Hầm </label>

                                    <div class="col-sm-9">
                                        <input type="number" id="numberOfBasement" placeholder="Số Tầng Hầm"
                                               class="col-xs-10 col-sm-5" class="form-control" name="numberOfBasement"
                                               value="${model.numberOfBasement}"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" for="types">
                                        Loại Tòa Nhà </label>
                                    <div class="col-sm-9">
                                        <form:checkboxes path="types" items="${mapTypes}"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" for="">
                                        Quận </label>
                                    <div class="col-sm-9">
                                        <form:select path="district" id="district">
                                            <form:option value="" label="---Chọn Quận---"/>
                                            <form:options items="${districts}"/>
                                        </form:select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right">
                                        Huyện</label>

                                    <div class="col-sm-9">
                                        <form:input path="ward" cssClass="col-sm-5"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" for="">
                                        Đường</label>

                                    <div class="col-sm-9">
                                        <input type="text" id="street" placeholder="Đường"
                                               class="col-xs-10 col-sm-5"
                                               class="form-control" name="street" value="${model.street}"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" for="">
                                        Hướng</label>

                                    <div class="col-sm-9">
                                        <input type="text" placeholder="Hướng"
                                               class="col-xs-10 col-sm-5"
                                               class="form-control" name="direction" value="${model.direction}"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" for="">
                                        Hạng</label>

                                    <div class="col-sm-9">
                                        <input type="text" placeholder="A"
                                               class="col-xs-10 col-sm-5"
                                               class="form-control" name="level" value="${model.level}"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" for="">
                                        Diện Tích Sàn</label>

                                    <div class="col-sm-9">
                                        <input type="text" placeholder="nhiêu đó"
                                               class="col-xs-10 col-sm-5"
                                               class="form-control" name="floorArea" value="${model.floorArea}"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" for="">
                                        Diện Tích Thuê</label>

                                    <div class="col-sm-9">
                                        <form:input path="rentArea" cssClass="col-sm-5"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" for="">
                                        Giá Thuê</label>

                                    <div class="col-sm-9">
                                        <input type="text" placeholder="14"
                                               class="col-xs-10 col-sm-5"
                                               class="form-control" name="rentPrice" value="${model.rentPrice}"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" for="">
                                        Mô tả giá</label>

                                    <div class="col-sm-9">
                                        <input type="text" placeholder="14$"
                                               class="col-xs-10 col-sm-5"
                                               class="form-control" name="rentPriceDescription" value="${model.rentPriceDescription}"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" for="">
                                        Phí dịch vụ</label>

                                    <div class="col-sm-9">
                                        <input type="text" placeholder="2.5$"
                                               class="col-xs-10 col-sm-5"
                                               class="form-control" name="serviceFee" value="${model.serviceFee}"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" for="">
                                        Phí Ô tô</label>

                                    <div class="col-sm-9">
                                        <input type="text" placeholder="80$/xe/tháng"
                                               class="col-xs-10 col-sm-5"
                                               class="form-control" name="carFee" value="${model.carFee}"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" for="">
                                        Phí mô tô</label>

                                    <div class="col-sm-9">
                                        <input type="text" placeholder="10$/xe/tháng"
                                               class="col-xs-10 col-sm-5"
                                               class="form-control" name="motoFee" value="${model.motoFee}"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" for="">
                                        Phí nước</label>

                                    <div class="col-sm-9">
                                        <input type="text" placeholder="10$/tháng"
                                               class="col-xs-10 col-sm-5"
                                               class="form-control" name="waterFee" value="${model.waterFee}"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" for="">
                                        Phí môi giới</label>

                                    <div class="col-sm-9">
                                        <input type="text" placeholder="10$/tháng"
                                               class="col-xs-10 col-sm-5"
                                               class="form-control" name="brokerageFee" value="${model.brokerageFee}"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" for="">
                                        Phí Ngoài giờ</label>

                                    <div class="col-sm-9">
                                        <input type="text" id="" placeholder="Free"
                                               class="col-xs-10 col-sm-5"
                                               class="form-control" name="overtimeFee" value="${model.overtimeFee}"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" for="">
                                        Tiền điện</label>

                                    <div class="col-sm-9">
                                        <input type="text" id="electricityFee" placeholder="3300đkw"
                                               class="col-xs-10 col-sm-5"
                                               class="form-control" name="electricityFee" value="${model.electricityFee}"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" for="">
                                        Đặt cọc</label>

                                    <div class="col-sm-9">
                                        <input type="text" id="deposit" placeholder="3 tháng"
                                               class="col-xs-10 col-sm-5"
                                               class="form-control" name="deposit" value="${model.deposit}"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" for="">
                                        Thanh toán</label>

                                    <div class="col-sm-9">
                                        <input type="text" id="" placeholder="Theo Quý"
                                               class="col-xs-10 col-sm-5"
                                               class="form-control" name="payment" value="${model.payment}"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" for="">
                                        Thời hạn thuê</label>

                                    <div class="col-sm-9">
                                        <input type="text" id="" placeholder="1-5 năm"
                                               class="col-xs-10 col-sm-5"
                                               class="form-control" name="rentTime" value="${model.rentTime}"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" for="">
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
                                                    Thêm Tòa Nhà
                                                </c:otherwise>
                                            </c:choose>
                                        </button>

                                        &nbsp; &nbsp; &nbsp;
                                        <button class="btn" type="reset">
                                            <i class="ace-icon fa fa-undo bigger-110"></i>
                                            Hủy
                                        </button>
                                    </div>
                                </div>

                                <div class="form-group mt-5">

                                </div>

                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.col -->


    </div><!-- /.row -->


</div><!-- /.row -->
<div id="assignmentBuildingModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Danh sách nhân viên</h4>
            </div>
            <div class="modal-body">
                <p>Some text in the modal.</p>
            </div>
            <div class="modal-footer">
                <button type="button" id="btnAssignmentBuilding" class="btn btn-success" data-dismiss="modal">Giao Tòa
                    Nhà
                </button>
                <button type="button" class="btn btn-primary" data-dismiss="modal">Đóng</button>
            </div>
        </div>

    </div>
</div>
<script>
    function assignmentBuilding() {
        assignmentBuildingModal();
    }

    function assignmentBuildingModal() {
        $('#assignmentBuildingModal').modal();
    }
</script>

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
        var buildingTypes = [];
        var formData = $('#formEdit').serializeArray();
        $.each(formData, function (index, v) {
            if (v.name == 'types') {
                buildingTypes.push(v.value);
            } else {
                data['' + v.name + ''] = v.value;
            }
        });
        data['types'] = buildingTypes;
        data['id'] = $('#btnAddBuilding').val();
        $.ajax({
            type: 'POST',
            url: '${buildingAPI}',
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

</script>

</body>
</html>
