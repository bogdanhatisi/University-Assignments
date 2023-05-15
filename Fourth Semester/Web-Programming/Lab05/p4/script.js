function sortTable(columnIndex) {
  let rows,
    currentRow,
    nextRow,
    shouldSwap,
    swapCount = 0;
  const table = $("#table");
  let isSorting = true;
  let sortOrder = "ASC";

  while (isSorting) {
    let i;
    isSorting = false;
    rows = table.find("tr");
    for (i = 1; i < rows.length - 1; i++) {
      shouldSwap = false;
      currentRow = $(rows[i]).find("td").eq(columnIndex);
      nextRow = $(rows[i + 1])
        .find("td")
        .eq(columnIndex);
      if (
        (sortOrder === "ASC" &&
          currentRow.html().toLowerCase() > nextRow.html().toLowerCase()) ||
        (sortOrder === "DESC" &&
          currentRow.html().toLowerCase() < nextRow.html().toLowerCase())
      ) {
        shouldSwap = true;
        break;
      }
    }
    if (shouldSwap) {
      $(rows[i]).before(rows[i + 1]);
      isSorting = true;
      swapCount++;
    } else {
      if (swapCount === 0 && sortOrder === "ASC") {
        sortOrder = "DESC";
        isSorting = true;
      }
    }
  }
}
