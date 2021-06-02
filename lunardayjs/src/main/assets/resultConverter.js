function lunarDayConverter(result){
    var fmt = "YYYY-MM-DD h:mm:ss a"
    return result.map(item => Object.assign({}, item, {start: item.start.format(fmt), end: item.end.format(fmt)}))
}