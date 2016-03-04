#ifndef CIFACE_H
#define CIFACE_H
#ifdef __cplusplus
extern "C" {
#endif
void * create_qpbo(int nodes,int edges);
void add_unary_energies(void * q,int node,double e_0,double e_1);
void add_pairwise_energies(void * q,int x,int y,double e_0_0,double e_0_1,double e_1_0,double e_1_1);
void find_solution(void * q);
int get_flag(void * q,int node);
void dispose_qpbo(void * q);
#ifdef __cplusplus
}
#endif
#endif