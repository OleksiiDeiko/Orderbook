# Orderbook
Implementation of a simple orderbook

## Implementation

Imagine that you are in a market which only trades one type of item (e.g. tomatoes of equal quality; generally known as "shares"). There will be a certain amount of tomatoes (shares) being offered at certain prices. Also, there will be people willing to buy at a certain price. Imagine that everyone who could buy/sell at an acceptable price(limit price) immediately does that and leaves the market. This way (most of the time), nobody can perform a trade right now and everyone has to wait until something changes (e.g. someone reconsiders "acceptable" price, or a new person appears). Those "limit orders" (people willing to buy/sell in certain quantities) are the limit order book. In some cases people are willing to buy/sell at any price (that's called a "market order"), such a person is always going to perform the trade and then leaves the market.

In other words, each price level (for simplicity let's think of it as an integer value) can be either bid (there are people willing to buy at this price), ask (people are willing to sell at this price) or spread (nobody is willing to buy or sell at this price).

Generally, the order book looks like the following example (B - bid, S - spread, A - ask), size defines how many shares can be bought/sold at this price:

|Price	|Size	|Type	|Comment|
|-------|-----|-----|-------|
|99	|0	|A	|Size is zero, but it is still an ask price, because it is higher than the best ask|
|98	|50	|A	|Best ask (lowest non-empty ask price level)|
|97	|0	|S	||
|96	|0	|S	||
|95	|40	|B	|Best bid (highest non-empty bid price level)|
|94	|30	|B	||
|93	|0	|B	||
|92	|77	|B	||


# Input/Output

## Input file

Each line in the file can be one of the following:

Updates to the limit order book in the following format:

- u,\<price\>,\<size\>,bid - set bid size at \<price\> to \<size\>

- u,\<price\>,\<size\>,ask - set ask size at \<price\> to \<size\>

Queries in the following format:

- q,best_bid - print best bid price and size

- q,best_ask - print best ask price and size

- q,size,\<price\> - print size at specified price (bid, ask or spread).

And market orders in the following format:

- o,buy,\<size\> - removes \<size\> shares out of asks, most cheap ones.

- o,sell,\<size\> - removes \<size\> shares out of bids, most expensive ones

### Example of input file

**u,9,1,bid**

**u,11,5,ask**

**q,best_bid**

**u,10,2,bid**

**q,best_bid**

**o,sell,1**

**q,size,10**

**u,9,0,bid**

**u,11,0,ask**


## Output file

### Example of output file

**9,1**

**10,2**

**1**
