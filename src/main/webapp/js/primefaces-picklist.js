PrimeFaces.widget.PickList.prototype.bindButtonEvents = function() {
	var a = this;
	PrimeFaces.skinButton(this.jq.find(".ui-button"));
	$(this.jqId + " .ui-picklist-button-add").click(function() {
		a.add();
	});
	$(this.jqId + " .ui-picklist-button-add-all").click(function() {
		a.addAll();
	});
	$(this.jqId + " .ui-picklist-button-remove").click(function() {
		a.remove();
	});
	$(this.jqId + " .ui-picklist-button-remove-all").click(function() {
		a.removeAll();
	});
	if (this.cfg.showSourceControls) {
		$(
				this.jqId
						+ " div.ui-picklist-source-controls .ui-picklist-button-move-up")
				.click(function() {
					a.moveUp(a.sourceList);
				});
		$(
				this.jqId
						+ " div.ui-picklist-source-controls .ui-picklist-button-move-top")
				.click(function() {
					a.moveTop(a.sourceList);
				});
		$(
				this.jqId
						+ " div.ui-picklist-source-controls .ui-picklist-button-move-down")
				.click(function() {
					a.moveDown(a.sourceList);
				});
		$(
				this.jqId
						+ " div.ui-picklist-source-controls .ui-picklist-button-move-bottom")
				.click(function() {
					a.moveBottom(a.sourceList);
				});
	}
	if (this.cfg.showTargetControls) {
		$(
				this.jqId
						+ " div.ui-picklist-target-controls .ui-picklist-button-move-up")
				.click(function() {
					a.moveUp(a.targetList);
				});
		$(
				this.jqId
						+ " div.ui-picklist-target-controls .ui-picklist-button-move-top")
				.click(function() {
					a.moveTop(a.targetList);
				});
		$(
				this.jqId
						+ " div.ui-picklist-target-controls .ui-picklist-button-move-down")
				.click(function() {
					a.moveDown(a.targetList);
				});
		$(
				this.jqId
						+ " div.ui-picklist-target-controls .ui-picklist-button-move-bottom")
				.click(function() {
					a.moveBottom(a.targetList);
				});
	}
};
