/******************************************/

~ Zipcode Range Resolver registers zip codes based on range. A Tree data structure is used to store the information as searching can be done in a time complexity of log(n). 

This process is handled by traversing left and right based on the value of lowerRange of a specific zip code. 
That is if input lowerRange is less than root lower range traversing is done to the left. 
If input lowerRange is greater than root lower range traversing id done on the right. Once the exact node where the range can be placed is found either the node is updated based on the range of the zip code or a new node is created if the range never existed.  

 
 /*********************************************/
