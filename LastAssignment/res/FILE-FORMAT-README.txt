To Load a STRATEGY from a FILE, the following skeleton should be followed.

*************************************************************************
TYPE: 		EQUAL/UNEQUAL						*
START DATE: 	YYYY-MM-DD						*
END DATE: 	YYYY-MM-DD						*
DAYS: 		int Num of recurring days				*
AMOUNT: 	Double Amount						*
COMMISSION: 	Double Amount						*
WEIGHTS: 	ticker,weight; ticker,weight; ticker,weight		* ---- If only Unequal weights strategy
END OF STRATEGY								*
*************************************************************************


To Load a PORTFOLIO from a FILE, the following skeleton should be followed.

*************************************************************************
START OF STOCKS								*
TICKER: <TICKER NAME>							*
YYYY-MM-DD,no of stocks,commission;					*
YYYY-MM-DD, no of stocks, commission;					*
END OF STOCK								*
TICKER: <TICKER NAME>							*
YYYY-MM-DD,no of stocks,commission;					*
YYYY-MM-DD,no of stocks,commission;					*
END OF STOCK								*
END OF STOCKS								*
START OF STRATEGIES							*
-STRATEGY								*
TYPE: 		EQUAL/UNEQUAL						*
START DATE: 	YYYY-MM-DD						*
END DATE: 	YYYY-MM-DD						*
DAYS: 		int Num of recurring days				*
AMOUNT: 	Double Amount						*
COMMISSION: 	Double Amount						*
WEIGHTS: 	ticker,weight; ticker,weight; ticker,weight		* ---- If only Unequal weights strategy
END OF STRATEGY								*
TYPE: 		EQUAL/UNEQUAL						*
START DATE: 	YYYY-MM-DD						*
END DATE: 	YYYY-MM-DD						*
DAYS: 		int Num of recurring days				*
AMOUNT: 	Double Amount						*
COMMISSION: 	Double Amount						*
WEIGHTS: 	ticker,weight; ticker,weight; ticker,weight		* ---- If only Unequal weights strategy
END OF STRATEGY								*
END OF STRATEGIES							*
END OF PORTFOLIO							*
*************************************************************************