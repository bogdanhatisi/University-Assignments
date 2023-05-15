function sortTable(columnIndex) {
  let rows,
    currentRow,
    nextRow,
    shouldSwap,
    swapCount = 0;
  const table = document.getElementById("table");
  let isSorting = true;
  let sortOrder = "ASC";

  while (isSorting) {
    let i;
    isSorting = false;
    rows = table.getElementsByTagName("tr");
    for (i = 1; i < rows.length - 1; i++) {
      shouldSwap = false;
      currentRow = rows[i].getElementsByTagName("td")[columnIndex];
      nextRow = rows[i + 1].getElementsByTagName("td")[columnIndex];
      if (
        (sortOrder === "ASC" &&
          currentRow.innerHTML.toLowerCase() >
            nextRow.innerHTML.toLowerCase()) ||
        (sortOrder === "DESC" &&
          currentRow.innerHTML.toLowerCase() < nextRow.innerHTML.toLowerCase())
      ) {
        shouldSwap = true;
        break;
      }
    }
    if (shouldSwap) {
      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
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
