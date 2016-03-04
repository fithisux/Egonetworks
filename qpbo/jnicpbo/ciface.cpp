#include "QPBO.h"
#include "qpbosolver_QPBOSolution.h"

typedef int REAL;
QPBO<REAL>* q;

/*
 * Class:     qpbosolver_QPBOSolution
 * Method:    create_qpbo
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_qpbosolver_QPBOSolution_create_1qpbo
  (JNIEnv * myenv, jobject myobj, jint nodes, jint edges){
	  q = new QPBO<REAL>(nodes, edges); // max number of nodes & edges
	  q->AddNode(nodes);
  }

/*
 * Class:     qpbosolver_QPBOSolution
 * Method:    add_unary_energies
 * Signature: (IDD)V
 */
JNIEXPORT void JNICALL Java_qpbosolver_QPBOSolution_add_1unary_1energies
  (JNIEnv *myenv, jobject myobj, jint node, jdouble e_0, jdouble e_1){
	  q->AddUnaryTerm(node, e_0,e_1);
  }

/*
 * Class:     qpbosolver_QPBOSolution
 * Method:    add_pairwise_energies
 * Signature: (IIDDDD)V
 */
JNIEXPORT void JNICALL Java_qpbosolver_QPBOSolution_add_1pairwise_1energies
  (JNIEnv * myenv, jobject myobj, jint x, jint y, jdouble e_0_0, jdouble e_0_1, jdouble e_1_0, jdouble e_1_1){
	  q->AddPairwiseTerm(x,y,e_0_0,e_0_1,e_1_0,e_1_1);
  }

/*
 * Class:     qpbosolver_QPBOSolution
 * Method:    find_solution
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_qpbosolver_QPBOSolution_find_1solution
  (JNIEnv * myenv, jobject myobj){
	  q->Solve();
		q->ComputeWeakPersistencies();

  }

/*
 * Class:     qpbosolver_QPBOSolution
 * Method:    get_flag
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_qpbosolver_QPBOSolution_get_1flag
  (JNIEnv *myenv, jobject myobj, jint node){
	  return (jint) q->GetLabel(node);
  }

/*
 * Class:     qpbosolver_QPBOSolution
 * Method:    dispose_qpbo
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_qpbosolver_QPBOSolution_dispose_1qpbo
  (JNIEnv *myenv, jobject myobj){
	  delete q;
  }