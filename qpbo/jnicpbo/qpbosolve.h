#ifndef QPBOSOLVER_H
#define QPBOSOLVER_H
#include <stdio.h>


typedef struct _unaryenergy {
	double e_0,e_1;
} unaryenergy;


typedef struct _pairwiseenergy {
	int x,y;
	double  e_0_0,e_0_1,e_1_0,e_1_1;
} pairwiseenergy;

typedef struct _qpbosolveinput{
	int nodes;
	int edges;	
	unaryenergy*	 unaryenergies;
	pairwiseenergy* pairwiseenergies;
}qpbosolveinput;

typedef struct _qpbosolveoutput{
	int valid;
	int* flags;
}qpbosolveoutput;


#ifdef BUILDWINDOWS
#ifdef BUILD_FOO
#    define FOOAPI __declspec(dllexport)
#else
#    define FOOAPI __declspec(dllimport)
#endif
#else
#define  FOOAPI 
#endif

FOOAPI void qpbo_solve(qpbosolveinput *input,qpbosolveoutput* output);

#endif