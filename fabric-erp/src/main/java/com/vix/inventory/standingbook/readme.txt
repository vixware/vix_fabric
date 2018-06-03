台账管理

创建台账视图

CREATE
    VIEW `V_INV_INVENTORYCURRENTSTOCKVIEW` 
    AS
select `inv`.`ID` AS `id`,`inv`.`TENANTID` AS `tenantId`,`inv`.`COMPANYCODE` AS `companyCode`,`inv`.`COMPANYINNERCODE` AS `CompanyInnerCode`,
`inv`.`massunitEndTime` AS `massunitEndTime`,
`w`.`PARENT_ID` AS `parentId`,`inv`.`itemcode` AS `itemcode`,`inv`.`skuCode` AS `skuCode`,`inv`.`itemname` AS `itemname`,
`inv`.`specification` AS `specification`,`inv`.`unit` AS `unit`,sum(`inv`.`quantity`) AS `quantity`,
sum(`inv`.`avaquantity`) AS `avaquantity`,`w`.`NAME` AS `warehouse`,`s`.`NAME` AS `invShelfName` 
from ((`inv_inventory` `inv` left join `inv_warehouse` `w` on((`inv`.`INVWAREHOUSE_ID` = `w`.`id`)))
 left join `inv_shelf` `s` on((`inv`.`INVSHELF_ID` = `s`.`id`))) group by `inv`.`skuCode`,`w`.`PARENT_ID`


CREATE
    VIEW `V_INV_PRODUCTVIEW` 
    AS
select `inv`.`ID` AS `id`,`inv`.`TENANTID` AS `tenantId`,`inv`.`COMPANYCODE` AS `companyCode`,`inv`.`COMPANYINNERCODE` AS `CompanyInnerCode`,
`item`.`CODE` AS `itemCode`,`item`.`NAME` AS `itemName`,sum(`inv`.`AMOUNT`) AS `amount`,`inv`.`UNIT` AS `unit`,`inv`.`PRICE` AS `price`,
(sum(`inv`.`AMOUNT`) * `inv`.`PRICE`) AS `netTotal` from `SALE_SALEORDERITEM` `inv`,mdm_item item where inv.item_id=item.ID group by `item`.SKUCODE

CREATE
    VIEW `V_INV_SUPPLIERSVIEW` 
    AS
select `inv`.`ID` AS `id`,`inv`.`TENANTID` AS `tenantId`,`inv`.`COMPANYCODE` AS `companyCode`,`inv`.`COMPANYINNERCODE` AS `CompanyInnerCode`,
`s`.`CODE` AS `channelDistributorCode`,`s`.`NAME` AS `channelDistributorName`,(sum(`inv`.`AMOUNT`) * `inv`.`PRICE`) AS `netTotal` 
from ((`SALE_SALEORDERITEM` `inv` left join `SALE_SALESORDER` `w` on((`inv`.salesorder_id = `w`.`ID`))) 
left join `drp_channeldistributor` `s` on((`w`.`channelDistributor_id` = `s`.`ID`))) where s.code is not null group by `s`.`CODE`