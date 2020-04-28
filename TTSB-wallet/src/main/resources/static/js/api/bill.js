function getAllBill(data){
	return postJson(urls.getAllBill, data);
}

function getBillByParam(data){
	return postJson(urls.getBillByParam, data);
}

function getBillByEntity(data){
	return postJson(urls.getBillByEntity, data);
}

function getBillDetail(data){
	return postJson(urls.getBillDetail, data);
}

function getAddressRecordByAddress(data){
	return postJson(urls.getAddressRecordByAddress, data);
}