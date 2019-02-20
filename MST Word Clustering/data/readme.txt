README:

1. Program runs with following arguments:
	args[0]-----> wordVec.txt
	args[1]-----> sample1_word_pairs.txt
	args[2]-----> number of Clusters(e.g 2,3 and so on.)

2. Program Execution time: 13-20 seconds.

3.Basic Algorithm:
	a) Creating Complete,undirected,weighted graph of words in word pairs file.
	b) Getting Minimum Spanning Tree of it for clustering process.(MST edges are in tree Vector in main method)
	c) Get Cluster by removing k-1 edges from MST through connected components by means of Depth First Search.
	d) Cluster are in Clusters.txt that will be created in src folder

4. You can find also my output samples for k=2 and k=3.
NOTE!!! There were plenty of negative cosine similarity values in the assignment. In order to prevent that, I have used (1-cosSimilarity)/2 for negative similarity values. Results can be a little bit different that is why. However most of the words are similar.

