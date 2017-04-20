$(document)
		.ready(
				function() {
					$("#datetime").datepicker( {
						picker : "<button>Select</button>",
						applyrule : rule
					});
					$("#endtime").datepicker( {
						picker : "<button>Select</button>",
						applyrule : rule
					});
					$("#gettime0")
							.datepicker(
									{
										picker : "<img class='picker' align='middle' src='./../resource/css/date/images/cal.gif' alt=''/>"
									});
					$("#hdobj").datepicker( {
						picker : "#handler", //this should be a hidden
						onReturn : function(d) {
							alert(d.Format("M/d, yyyy"));
							$("#target").html(d.Format("M/d, yyyy"));
						}
					});
					function rule(id) {
						if (id == 'datetime') {
							var v = $("#endtime").val();
							if (v == "") {
								return null;
							} else {
								var d = v
										.match(/^(\d{1,4})(-|\/|.)(\d{1,2})\2(\d{1,2})$/);
								if (d != null) {
									var nd = new Date(parseInt(d[1], 10),
											parseInt(d[3], 10) - 1, parseInt(
													d[4], 10));
									return {
										enddate : nd
									};
								} else {
									return null;
								}
							}
						} else {
							var v = $("#datetime").val();
							if (v == "") {
								return null;
							} else {
								var d = v
										.match(/^(\d{1,4})(-|\/|.)(\d{1,2})\2(\d{1,2})$/);
								if (d != null) {
									var nd = new Date(parseInt(d[1], 10),
											parseInt(d[3], 10) - 1, parseInt(
													d[4], 10));
									return {
										startdate : nd
									};
								} else {
									return null;
								}
							}

						}
					}
				});




				$(document)
		.ready(
				function() {
					$("#datetime").datepicker( {
						picker : "<button>Select</button>",
						applyrule : rule
					});
					$("#endtime").datepicker( {
						picker : "<button>Select</button>",
						applyrule : rule
					});
					$("#gettime1")
							.datepicker(
									{
										picker : "<img class='picker' align='middle' src='./../resource/css/date/images/cal.gif' alt=''/>"
									});
					$("#hdobj").datepicker( {
						picker : "#handler", //this should be a hidden
						onReturn : function(d) {
							alert(d.Format("M/d, yyyy"));
							$("#target").html(d.Format("M/d, yyyy"));
						}
					});
					function rule(id) {
						if (id == 'datetime') {
							var v = $("#endtime").val();
							if (v == "") {
								return null;
							} else {
								var d = v
										.match(/^(\d{1,4})(-|\/|.)(\d{1,2})\2(\d{1,2})$/);
								if (d != null) {
									var nd = new Date(parseInt(d[1], 10),
											parseInt(d[3], 10) - 1, parseInt(
													d[4], 10));
									return {
										enddate : nd
									};
								} else {
									return null;
								}
							}
						} else {
							var v = $("#datetime").val();
							if (v == "") {
								return null;
							} else {
								var d = v
										.match(/^(\d{1,4})(-|\/|.)(\d{1,2})\2(\d{1,2})$/);
								if (d != null) {
									var nd = new Date(parseInt(d[1], 10),
											parseInt(d[3], 10) - 1, parseInt(
													d[4], 10));
									return {
										startdate : nd
									};
								} else {
									return null;
								}
							}

						}
					}
				});



		$(document)
		.ready(
				function() {
					$("#datetime").datepicker( {
						picker : "<button>Select</button>",
						applyrule : rule
					});
					$("#endtime").datepicker( {
						picker : "<button>Select</button>",
						applyrule : rule
					});
					$("#gettime2")
							.datepicker(
									{
										picker : "<img class='picker' align='middle' src='./../resource/css/date/images/cal.gif' alt=''/>"
									});
					$("#hdobj").datepicker( {
						picker : "#handler", //this should be a hidden
						onReturn : function(d) {
							alert(d.Format("M/d, yyyy"));
							$("#target").html(d.Format("M/d, yyyy"));
						}
					});
					function rule(id) {
						if (id == 'datetime') {
							var v = $("#endtime").val();
							if (v == "") {
								return null;
							} else {
								var d = v
										.match(/^(\d{1,4})(-|\/|.)(\d{1,2})\2(\d{1,2})$/);
								if (d != null) {
									var nd = new Date(parseInt(d[1], 10),
											parseInt(d[3], 10) - 1, parseInt(
													d[4], 10));
									return {
										enddate : nd
									};
								} else {
									return null;
								}
							}
						} else {
							var v = $("#datetime").val();
							if (v == "") {
								return null;
							} else {
								var d = v
										.match(/^(\d{1,4})(-|\/|.)(\d{1,2})\2(\d{1,2})$/);
								if (d != null) {
									var nd = new Date(parseInt(d[1], 10),
											parseInt(d[3], 10) - 1, parseInt(
													d[4], 10));
									return {
										startdate : nd
									};
								} else {
									return null;
								}
							}

						}
					}
				});

	